package io.github.liqiha0.magicpen.workflow.source.json

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.treeToValue
import io.github.liqiha0.magicpen.workflow.*
import io.github.liqiha0.magicpen.workflow.data.Boolean
import io.github.liqiha0.magicpen.workflow.data.Number
import io.github.liqiha0.magicpen.workflow.data.Text
import io.github.liqiha0.magicpen.workflow.processcontrol.If
import java.io.InputStream
import java.math.BigDecimal
import java.util.*

private val builtinActionPackage = BuiltinActionPackage()

class WorkflowJsonParser(
    actionPackageFactories: Iterable<ActionPackageFactory> = emptyList()
) {
    private val objectMapper = jacksonObjectMapper()
    private val allActionPackageFactories: List<ActionPackageFactory> =
        actionPackageFactories + ServiceLoader.load(ActionPackageFactory::class.java)

    init {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    fun parse(inputStream: InputStream): Workflow {
        return this.parse(objectMapper.readTree(inputStream))
    }

    fun parse(json: String): Workflow {
        return this.parse(objectMapper.readTree(json))
    }

    fun parse(workflowJson: JsonNode): Workflow {
        val workflowModel = objectMapper.treeToValue<WorkflowModel>(workflowJson)
        val packages = this.parsePackages(workflowJson)

        return Workflow(this.parseProcess(workflowModel.nodes, packages))
    }

    fun parsePackages(workflowJson: JsonNode): Map<String, ActionPackage> {
        val workflowModel = objectMapper.treeToValue<WorkflowModel>(workflowJson)
        val repositoryMap: MutableMap<String, ActionPackage> = mutableMapOf()
        workflowModel.packages?.forEach {
            val type = it.source
            val factory = allActionPackageFactories.find { it.type == type }
                ?: throw NoSuchElementException("Package source: $type")
            val repo = factory.create(it.name)
            repositoryMap[it.id] = repo
        }
        return repositoryMap
    }

    fun parseProcess(nodes: Collection<NodeModel>, packages: Map<String, ActionPackage>): List<Executable> {
        val nodeCache: MutableMap<String, Executable> = mutableMapOf()
        fun parseAction(nodeModel: NodeModel): Executable {
            if (nodeModel.id != null) nodeCache[nodeModel.id]?.let { return it }

            val dependencies: MutableList<Executable> = mutableListOf()
            nodeModel.dependencies?.forEach { id ->
                val cached = nodeCache[id]
                if (cached != null) {
                    dependencies.add(cached)
                    return@forEach
                }

                val actionModel = nodes.find { it.id == id }
                    ?: throw NoSuchElementException("Not found action node: id=$id")
                dependencies.add(parseAction(actionModel))
            }

            return when {
                nodeModel.`if` != null -> If(
                    this.parseArgument(nodeModel.`if`.condition, nodeCache),
                    this.parseProcess(nodeModel.`if`.then, packages),
                    if (nodeModel.`if`.`else` != null) this.parseProcess(nodeModel.`if`.`else`, packages) else null,
                    dependencies
                )
                nodeModel.action != null -> {
                    val actionPackage = if (nodeModel.action.packageId != null) {
                        packages[nodeModel.action.packageId]
                    } else {
                        builtinActionPackage
                    }
                    val action = actionPackage?.getAction(nodeModel.action.name)
                        ?: throw NoSuchElementException("Cannot find the action named ${nodeModel.action.name}")

                    val arguments: MutableMap<String, Argument> = mutableMapOf()
                    nodeModel.action.arguments?.forEach {
                        arguments[it.key] = this.parseArgument(it.value, nodeCache)
                    }
                    val actionNode = ActionNode(action, arguments, dependencies)
                    nodeModel.id?.let {
                        nodeCache[it] = actionNode
                    }
                    actionNode
                }
                else -> throw JsonFormatException()
            }
        }
        return nodes.map(::parseAction)
    }

    private fun parseArgument(argumentModel: ArgumentModel, nodeCache: Map<String, Executable>): Argument {
        return when (argumentModel.type) {
            ArgumentType.TEXT -> StaticArgument(Text(argumentModel.value))
            ArgumentType.NUMBER -> StaticArgument(Number(BigDecimal(argumentModel.value)))
            ArgumentType.BOOLEAN -> StaticArgument(Boolean(argumentModel.value.toBoolean()))
            ArgumentType.NODE_RESULT -> {
                val index = argumentModel.value.indexOf(":")
                if (index != -1) {
                    val nodeId = argumentModel.value.substring(0, index)
                    val name = argumentModel.value.substring(index + 1, argumentModel.value.length)
                    NodeResultArgument(nodeCache[nodeId]!!, name)
                } else {
                    throw JsonFormatException()
                }
            }
        }
    }
}
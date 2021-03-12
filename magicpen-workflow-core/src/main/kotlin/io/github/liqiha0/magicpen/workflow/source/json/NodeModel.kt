package io.github.liqiha0.magicpen.workflow.source.json

data class NodeModel(
    val `if`: IfModel? = null,
    val action: ActionModel? = null,
    val id: String?,
    val dependencies: List<String>?
)

data class IfModel(val condition: ArgumentModel, val then: List<NodeModel>, val `else`: List<NodeModel>?)

data class ActionModel(
    val packageId: String?,
    val name: String,
    val arguments: Map<String, ArgumentModel>?,
)

data class ArgumentModel(
    val type: ArgumentType,
    val value: String
)

enum class ArgumentType {
    TEXT, NUMBER, NODE_RESULT, BOOLEAN
}
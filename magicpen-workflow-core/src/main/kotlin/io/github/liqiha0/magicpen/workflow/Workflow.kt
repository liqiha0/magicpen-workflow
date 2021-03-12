package io.github.liqiha0.magicpen.workflow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

private val defaultCoroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Default)

class Workflow(
    val nodes: Collection<Executable>,
) {
    private val process = Process(nodes)

    fun execute(
        components: Map<String, Any> = emptyMap(),
        coroutineScope: CoroutineScope = defaultCoroutineScope
    ): WorkflowContext {
        val workflowContext = WorkflowContext(coroutineScope, components)
        runBlocking(coroutineScope.coroutineContext) {
            process.execute(workflowContext)
        }
        return workflowContext
    }

    fun execute(components: Map<String, Any> = emptyMap()): WorkflowContext = this.execute(
        components,
        defaultCoroutineScope
    )
}

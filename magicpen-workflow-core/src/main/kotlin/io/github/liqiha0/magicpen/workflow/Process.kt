package io.github.liqiha0.magicpen.workflow

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Semaphore

class Process(val nodes: Collection<Executable>) {
    suspend fun execute(workflowContext: WorkflowContext) = coroutineScope {
        nodes.forEach { node ->
            launch {
                val semaphores = node.dependencies.map { Semaphore(1, 1) }
                workflowContext.eventBus.listen<ActionExecutedEvent> {
                    val index = node.dependencies.indexOf(it.actionNode)
                    if (index != -1) semaphores[index].release()
                }
                semaphores.forEach { it.acquire() }
                node.execute(workflowContext)
            }
        }
    }
}
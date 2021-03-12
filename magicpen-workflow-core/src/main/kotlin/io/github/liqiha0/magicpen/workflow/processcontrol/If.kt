package io.github.liqiha0.magicpen.workflow.processcontrol

import io.github.liqiha0.magicpen.workflow.Argument
import io.github.liqiha0.magicpen.workflow.Executable
import io.github.liqiha0.magicpen.workflow.Process
import io.github.liqiha0.magicpen.workflow.WorkflowContext
import kotlinx.coroutines.coroutineScope

class If(
    val condition: Argument,
    val then: Collection<Executable>,
    val `else`: Collection<Executable>? = null,
    override val dependencies: Collection<Executable> = emptyList()
) : Executable {
    private val thenProcess = Process(then)
    private val elseProcess: Process? = if (`else` != null) Process(`else`) else null
    override suspend fun execute(workflowContext: WorkflowContext): Unit = coroutineScope {
        val value = condition.getValue(workflowContext)

        if (value != null && value.toBoolean()) {
            thenProcess.execute(workflowContext)
        } else {
            elseProcess?.execute(workflowContext)
        }
    }
}
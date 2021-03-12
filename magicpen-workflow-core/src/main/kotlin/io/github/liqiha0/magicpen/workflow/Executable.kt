package io.github.liqiha0.magicpen.workflow

interface Executable {
    val dependencies: Collection<Executable>
    suspend fun execute(workflowContext: WorkflowContext)
}
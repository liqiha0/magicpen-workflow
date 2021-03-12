package io.github.liqiha0.magicpen.workflow

interface Argument {
    fun getValue(workflowContext: WorkflowContext): Data?
}

class NodeResultArgument(private val node: Executable, private val name: String) : Argument {
    override fun getValue(workflowContext: WorkflowContext): Data? {
        return workflowContext.nodeResults[node]?.get(name) ?: throw NoSuchElementException()
    }
}

class StaticArgument(private val value: Data) : Argument {
    override fun getValue(workflowContext: WorkflowContext): Data? {
        return value
    }
}
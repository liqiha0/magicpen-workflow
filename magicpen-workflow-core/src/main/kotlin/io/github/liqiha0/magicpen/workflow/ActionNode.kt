package io.github.liqiha0.magicpen.workflow

class ActionNode(
    val action: Action,
    val arguments: Map<String, Argument> = emptyMap(),
    override val dependencies: Collection<Executable> = emptyList()
) : Executable {
    override suspend fun execute(workflowContext: WorkflowContext) {
        val argumentValues = arguments.mapValues {
            it.value.getValue(workflowContext)
        }
        this.action.execute(ActionContext(argumentValues, workflowContext))
            ?.let { workflowContext.nodeResults[this] = it }
        workflowContext.eventBus.publish(ActionExecutedEvent(this))
    }
}

class ActionContext internal constructor(
    val arguments: Map<String, Data?>,
    val workflowContext: WorkflowContext
) {
    inline fun <reified T : Data> getArgument(name: String): T? {
        val data = this.arguments[name]
        if (data == null) return data

        if (data is T) {
            return data
        } else {
            throw UnexpectedDataTypeException(data.type)
        }
    }

    fun <T> getComponent(name: String): T? {
        return this.workflowContext.getComponent(name)
    }

    fun publishEvent(event: Any) = this.workflowContext.eventBus.publish(event)
}

class ActionExecutedEvent internal constructor(val actionNode: ActionNode)

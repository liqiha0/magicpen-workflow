package io.github.liqiha0.magicpen.workflow.action

import io.github.liqiha0.magicpen.workflow.*
import io.github.liqiha0.magicpen.workflow.data.Boolean

val EQUAL_DESCRIPTION = ActionDescription(
    "相等", "equal",
    listOf(
        ActionParameterDescription("值", "operand1"),
        ActionParameterDescription("值", "operand2")
    ),
    listOf(ActionResultDescription("结果", "result", "boolean"))
)

object Equal : Action {
    override fun execute(context: ActionContext): Map<String, Data>? {
        val operand1: Data? = context.getArgument("operand1")
        val operand2: Data? = context.getArgument("operand2")

        return mapOf("result" to Boolean(operand1 == operand2))
    }
}
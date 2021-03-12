package io.github.liqiha0.magicpen.workflow.action.math

import io.github.liqiha0.magicpen.workflow.*
import io.github.liqiha0.magicpen.workflow.data.Number
import java.math.BigDecimal

val ADD_DESCRIPTION = ActionDescription(
    "加", "add",
    listOf(
        ActionParameterDescription("值", "operand1"),
        ActionParameterDescription("值", "operand2")
    ),
    listOf(ActionResultDescription("结果", "result", "number"))
)

object Add : Action {
    override fun execute(context: ActionContext): Map<String, Data>? {
        val operand1: Number = context.getArgument("operand1") ?: throw UnexpectedDataTypeException()
        val operand2: Number? = context.getArgument("operand2")

        return mapOf("result" to Number(operand1.value + (operand2?.value ?: BigDecimal.ZERO)))
    }
}
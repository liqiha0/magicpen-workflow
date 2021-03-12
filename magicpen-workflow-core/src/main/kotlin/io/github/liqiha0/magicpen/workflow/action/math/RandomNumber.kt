package io.github.liqiha0.magicpen.workflow.action.math

import io.github.liqiha0.magicpen.workflow.Action
import io.github.liqiha0.magicpen.workflow.ActionContext
import io.github.liqiha0.magicpen.workflow.Data
import io.github.liqiha0.magicpen.workflow.data.Number
import kotlin.random.Random

object RandomNumber : Action {
    override fun execute(context: ActionContext): Map<String, Data>? {
        return mapOf("result" to Number(Random.nextInt().toBigDecimal()))
    }
}
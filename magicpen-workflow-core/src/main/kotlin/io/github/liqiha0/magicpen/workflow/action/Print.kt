package io.github.liqiha0.magicpen.workflow.action

import io.github.liqiha0.magicpen.workflow.Action
import io.github.liqiha0.magicpen.workflow.ActionContext
import io.github.liqiha0.magicpen.workflow.Data

object Print : Action {
    override fun execute(context: ActionContext): Map<String, Data>? {
        val message: Data? = context.getArgument("message")
        println(message)
        return message?.let { return mapOf("message" to message) }
    }
}
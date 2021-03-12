package io.github.liqiha0.magicpen.workflow

interface Action {
    fun execute(context: ActionContext): Map<String, Data>?
}
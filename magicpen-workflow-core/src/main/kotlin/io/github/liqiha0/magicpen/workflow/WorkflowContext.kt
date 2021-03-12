package io.github.liqiha0.magicpen.workflow

import kotlinx.coroutines.CoroutineScope


class WorkflowContext internal constructor(
    coroutineScope: CoroutineScope,
    private val components: Map<String, Any> = emptyMap()
) {
    val eventBus = EventBus(coroutineScope)
    val nodeResults: MutableMap<Executable, Map<String, Data>> = mutableMapOf()

    fun <T> getComponent(name: String): T? {
        @Suppress("unchecked_cast")
        return this.components[name] as? T?
    }
}
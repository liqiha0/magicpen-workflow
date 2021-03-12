package io.github.liqiha0.magicpen.workflow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.reflect.KClass

class EventBus(private val coroutineScope: CoroutineScope) {
    private val eventFlow = MutableSharedFlow<Any>(replay = Int.MAX_VALUE)

    fun <T : Any> listen(kClass: KClass<T>, block: (T) -> Unit) {
        coroutineScope.launch {
            eventFlow.filter {
                kClass.isInstance(it)
            }.collect { @Suppress("unchecked_cast") block(it as T) }
        }
    }

    fun publish(value: Any) {
        runBlocking(coroutineScope.coroutineContext) {
            eventFlow.emit(value)
        }
    }
}

inline fun <reified T : Any> EventBus.listen(noinline block: (T) -> Unit) {
    this.listen(T::class, block)
}
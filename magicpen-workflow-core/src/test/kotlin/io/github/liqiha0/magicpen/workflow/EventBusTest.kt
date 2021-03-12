package io.github.liqiha0.magicpen.workflow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.util.concurrent.CompletableFuture
import kotlin.test.Test
import kotlin.test.assertEquals

internal class EventBusTest {
    private val eventBus = EventBus(CoroutineScope(Dispatchers.Default))

    @Test
    fun publish() {
        val future = CompletableFuture<String>()

        data class TestEvent(val value: String)
        eventBus.listen<TestEvent> {
            future.complete(it.value)
        }
        eventBus.publish(TestEvent("test"))
        assertEquals(future.get(), "test")
    }
}
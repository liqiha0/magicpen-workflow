package io.github.liqiha0.magicpen.workflow

import io.github.liqiha0.magicpen.workflow.data.Text
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

internal class NodeResultArgumentTest {
    private val node = ActionNode(TestAction())
    private val workflowContext = WorkflowContext(CoroutineScope(Dispatchers.Default)).apply {
        this.nodeResults[node] = mapOf("hello" to Text("world"))
    }
    private val nodeResultArgument = NodeResultArgument(node, "hello")

    @DisplayName("节点结果")
    @Test
    fun test1() {
        val result = nodeResultArgument.getValue(workflowContext)
        assertNotNull(result)
    }
}
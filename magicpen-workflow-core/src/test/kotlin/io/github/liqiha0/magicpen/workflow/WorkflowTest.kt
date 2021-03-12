package io.github.liqiha0.magicpen.workflow

import io.github.liqiha0.magicpen.workflow.data.Text
import org.junit.jupiter.api.Test

internal class WorkflowTest {
    private val workflow: Workflow

    init {
        val firstAction = ActionNode(TestAction(), mapOf("param1" to StaticArgument(Text("value1"))))
        this.workflow = Workflow(
            listOf(
                firstAction,
                ActionNode(
                    TestAction(), mapOf("param2" to StaticArgument(Text("value2"))), listOf(firstAction)
                )
            )
        )
    }

    @Test
    fun execute() {
        workflow.execute()
    }
}
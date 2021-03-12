package io.github.liqiha0.magicpen.workflow

import io.github.liqiha0.magicpen.workflow.source.json.WorkflowJsonParser
import kotlin.test.Test
import kotlin.test.assertNotNull


internal class WorkflowJsonParserTest {

    private val workflowJsonParser = WorkflowJsonParser()

    @Test
    fun parse() {
        val workflow = workflowJsonParser.parse(
            this.javaClass.classLoader.getResourceAsStream("test.workflow.json")
        )
        assertNotNull(workflow)
    }
}
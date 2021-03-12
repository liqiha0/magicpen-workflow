package io.github.liqiha0.magicpen.workflow.cli

import io.github.liqiha0.magicpen.workflow.source.json.WorkflowJsonParser
import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    if (args.size != 1) {
        return
    }
    val path = args[0].replace("~", System.getProperty("user.home"))
    val fileContent = Files.readString(Paths.get(path))
    val workflow = WorkflowJsonParser().parse(fileContent)
    workflow.execute()
}
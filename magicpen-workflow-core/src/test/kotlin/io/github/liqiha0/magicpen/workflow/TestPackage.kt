package io.github.liqiha0.magicpen.workflow

import org.junit.platform.commons.logging.Logger
import org.junit.platform.commons.logging.LoggerFactory

class TestPackage : ActionPackage {
    override val manifest: PackageManifest
        get() = TODO("Not yet implemented")

    override fun getAction(name: String): Action {
        return when (name) {
            "test" -> TestAction()
            else -> throw NoSuchElementException()
        }
    }
}

val log: Logger = LoggerFactory.getLogger(TestAction::class.java)

internal class TestAction : Action {
    override fun execute(context: ActionContext): Map<String, Data>? {
        context.arguments.forEach { (k, v) ->
            log.info { "$k $v" }
        }
        return context.arguments["param1"]?.let { mapOf("param1" to it) }
    }
}

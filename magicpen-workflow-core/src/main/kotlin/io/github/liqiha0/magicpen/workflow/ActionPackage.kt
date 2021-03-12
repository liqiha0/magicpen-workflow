package io.github.liqiha0.magicpen.workflow

import io.github.liqiha0.magicpen.workflow.action.EQUAL_DESCRIPTION
import io.github.liqiha0.magicpen.workflow.action.Equal
import io.github.liqiha0.magicpen.workflow.action.Print
import io.github.liqiha0.magicpen.workflow.action.math.*

interface ActionPackage {
    val manifest: PackageManifest
    fun getAction(name: String): Action
}

class NoSuchActionException(val name: String) : RuntimeException(name)

private val BUILTIN_PACKAGE_MANIFEST = PackageManifest(
    listOf(
        EQUAL_DESCRIPTION,
        ADD_DESCRIPTION,
        SUBTRACT_DESCRIPTION,
        MULTIPLY_DESCRIPTION,
        DIVIDE_DESCRIPTION
    )
)

class BuiltinActionPackage : ActionPackage {
    override val manifest: PackageManifest get() = BUILTIN_PACKAGE_MANIFEST

    override fun getAction(name: String): Action {
        return when (name) {
            "random-number" -> RandomNumber
            "add" -> Add
            "subtract" -> Subtract
            "multiply" -> Multiply
            "divide" -> Divide
            "equal" -> Equal
            "print" -> Print
            else -> throw NoSuchActionException(name)
        }
    }
}
package io.github.liqiha0.magicpen.workflow.jvm

import io.github.liqiha0.magicpen.workflow.ActionPackage
import io.github.liqiha0.magicpen.workflow.ActionPackageFactory

class MavenActionPackageFactory : ActionPackageFactory {
    override val type: String get() = "maven"

    override fun create(name: String): ActionPackage {
        val tuple = name.split("::")
        return if (tuple.size == 2) {
            MavenActionPackage(tuple[1], tuple[0])
        } else {
            MavenActionPackage(tuple[0])
        }
    }
}
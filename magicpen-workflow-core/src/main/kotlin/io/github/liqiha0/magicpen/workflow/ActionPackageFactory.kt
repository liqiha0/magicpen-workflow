package io.github.liqiha0.magicpen.workflow

interface ActionPackageFactory {
    val type: String
    fun create(name: String): ActionPackage
}
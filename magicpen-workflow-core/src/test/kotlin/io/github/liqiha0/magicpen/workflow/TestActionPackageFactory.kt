package io.github.liqiha0.magicpen.workflow

class TestActionPackageFactory : ActionPackageFactory {
    override val type: String get() = "test"
    override fun create(name: String): ActionPackage {
        return TestPackage()
    }
}
package io.github.liqiha0.magicpen.workflow.jvm

import org.junit.jupiter.api.DisplayName
import kotlin.test.Test
import kotlin.test.assertNotNull

internal class MavenActionPackageFactoryTest {
    private val factory = MavenActionPackageFactory()

    @DisplayName("创建默认仓库MavenActionPackage")
    @Test
    fun test1() {
        val actionPackage = factory.create("org.eclipse.aether:aether-api:1.1.0")
        assertNotNull(actionPackage)
    }

    @DisplayName("创建其他仓库MavenActionPackage")
    @Test
    fun test2() {
        val actionPackage =
            factory.create("https://nexus.juzhen.synology.me:8443/repository/maven-public/::io.github.liqiha0.magicpen:magicpen-workflow-stdlib:0.0.1-20211211.092905-1")
        assertNotNull(actionPackage)
    }
}
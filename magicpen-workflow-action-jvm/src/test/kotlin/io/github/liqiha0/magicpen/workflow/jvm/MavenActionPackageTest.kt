package io.github.liqiha0.magicpen.workflow.jvm

import org.junit.jupiter.api.DisplayName
import kotlin.test.Test
import kotlin.test.assertNotNull

internal class MavenActionPackageTest {
    private val mavenActionPackage = MavenActionPackage(
        "io.github.liqiha0.magicpen:magicpen-workflow-stdlib:0.0.1-20211211.092905-1",
        "https://nexus.juzhen.synology.me:8443/repository/maven-public/"
    )

    @DisplayName("获取Action")
    @Test
    fun test1() {
        val action = mavenActionPackage.getAction("action.Number")
        assertNotNull(action)
    }
}
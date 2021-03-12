plugins {
    kotlin("jvm") version "1.6.0"
    id("com.github.johnrengelman.shadow") version "7.1.0"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":magicpen-workflow-core"))
    implementation(project(":magicpen-workflow-action-jvm"))
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.4")
    testImplementation(kotlin("test"))
}

application {
    mainClass.set("io.github.liqiha0.magicpen.workflow.cli.MainKt")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "io.github.liqiha0.magicpen.workflow.cli.MainKt"
    }
}

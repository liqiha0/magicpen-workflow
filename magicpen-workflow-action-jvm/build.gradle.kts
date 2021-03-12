import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":magicpen-workflow-core"))
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation("org.eclipse.aether:aether-api:1.1.0")
    implementation("org.eclipse.aether:aether-connector-basic:1.1.0")
    implementation("org.eclipse.aether:aether-transport-http:1.1.0")
    implementation("org.apache.maven:maven-aether-provider:3.3.9")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}
plugins {
    kotlin("jvm") version "1.6.0"
}

group = "com.github.devlaq"
version = "1.0-dev2"

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public")
    maven("https://jitpack.io")
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")

    implementation("io.papermc.paper:paper-api:${project.properties["libraries.papermc.version"]}")

    //Tap, Kommand, Heartbeat-coroutines
    implementation("io.github.monun:tap-api:${project.properties["libraries.tap.version"]}")
    implementation("io.github.monun:kommand-api:${project.properties["libraries.kommand.version"]}")
    implementation("io.github.monun:heartbeat-coroutines:${project.properties["libraries.heartbeat-coroutines.version"]}")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

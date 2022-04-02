plugins {
    kotlin("jvm") version "1.5.10"
}

group = "com.github.devlaq"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public")
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation("io.papermc.paper:paper-api:${project.properties["libraries.papermc.version"]}")

    //Tap, Kommand
    implementation("io.github.monun:tap-api:${project.properties["libraries.tap.version"]}")
    implementation("io.github.monun:kommand-api:${project.properties["libraries.kommand.version"]}")
}
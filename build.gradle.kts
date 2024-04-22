plugins {
    id("java")
    kotlin("jvm") version "1.9.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("xyz.jpenilla.run-paper") version "2.2.0"
}

group = "ink.nostal.timebottle"
version = "1.0.0"

repositories {
    mavenCentral()
    mavenLocal()
    maven(uri("https://repo.papermc.io/repository/maven-public/"))
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
}

tasks {
    runServer {
        minecraftVersion("1.20.4")
    }
}

kotlin {
    jvmToolchain(17)
}

tasks.processResources {
    filesMatching("plugin.yml") {
        expand(mapOf("version" to project.version))
    }
}
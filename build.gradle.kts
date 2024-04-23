plugins {
    id("java")
    kotlin("plugin.serialization") version "1.9.23"
    kotlin("jvm") version "1.9.21"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("xyz.jpenilla.run-paper") version "2.2.3"
}

fun kotlinDep(s: String): String {
    return "org.jetbrains.kotlin.$s"
}

allprojects {
    apply {
        plugin("java")
        plugin("com.github.johnrengelman.shadow")
        plugin("java-library")
        plugin(kotlinDep("jvm"))
        plugin(kotlinDep("plugin.serialization"))
    }

    group = "ink.nostal.timebottle"
    version = "1.0.0"

    val bukkitAPIVersion by extra("1.13")

    repositories {
        mavenCentral()
        mavenLocal()
        maven(uri("https://repo.papermc.io/repository/maven-public/"))
        maven(uri("https://maven.nostal.ink/repository/maven-public"))
        maven(uri("https://oss.sonatype.org/content/repositories/snapshots/"))
    }

    dependencies {
        compileOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
        compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
        compileOnly("org.incendo:cloud-paper:2.0.0-beta.4")
        compileOnly("org.incendo:cloud-kotlin-coroutines:2.0.0-beta.4")
        compileOnly("org.mongodb:mongodb-driver-kotlin-coroutine:5.0.1")
        compileOnly("com.github.shynixn.mccoroutine:mccoroutine-folia-api:2.15.0")
        compileOnly("com.github.shynixn.mccoroutine:mccoroutine-folia-core:2.15.0")
    }

    tasks.processResources {
        inputs.property("version", rootProject.version)
        inputs.property("api", bukkitAPIVersion)

        filesMatching("plugin.yml") {
            expand("version" to version, "api" to bukkitAPIVersion)
        }
    }
}

val paperPlugins = runPaper.downloadPluginsSpec {
    url("https://ci.lucko.me/job/spark/401/artifact/spark-bukkit/build/libs/spark-1.10.60-bukkit.jar")
    // url("https://github.com/dmulloy2/ProtocolLib/releases/download/5.2.0/ProtocolLib.jar")
}

runPaper.folia.registerTask()

tasks.runServer {
    minecraftVersion("1.20.4")
    downloadPlugins.from(paperPlugins)
}

kotlin {
    jvmToolchain(17)
}
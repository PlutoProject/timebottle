package ink.nostal.timebottle.utils

import ink.nostal.timebottle.platform

private const val FOLIA_CLASS = "io.papermc.paper.threadedregions.RegionizedServer"
private const val PAPER_CLASS = "com.destroystokyo.paper.VersionHistoryManager"
private val supportedPlatforms = listOf("paper", "folia", "purpur", "pufferfish", "leaves", "leaf", "luminol", "lumina")
private val platformName = run {
    val craftServerClass = platform.javaClass
    val server = platform
    val craftServer = craftServerClass.cast(server)
    val field = craftServer.javaClass.getDeclaredField("serverName")
    field.isAccessible = true
    (field.get(craftServer) as String).lowercase()
}

val isFolia: Boolean = try {
    Class.forName(FOLIA_CLASS)
    true
} catch (e: Exception) {
    false
}

val isPaper: Boolean = try {
    Class.forName(PAPER_CLASS)
    true
} catch (e: Exception) {
    false
}

val minecraftVersion: Triple<Int, Int, Int> = run {
    val version = platform.minecraftVersion
    val firstDot = version.indexOf('.')
    val secondDot = version.indexOf('.', firstDot + 1)

    val major = version.substring(0, firstDot).toInt()
    val minor = version.substring(firstDot + 1, if (secondDot != -1) secondDot else version.length).toInt()
    val patch = if (secondDot != -1) version.substring(secondDot + 1).toInt() else 0

    Triple(major, minor, patch)
}

val isSupportedVersion = minecraftVersion.second >= 20

val isSupportedPlatform = supportedPlatforms.any { it == platformName }
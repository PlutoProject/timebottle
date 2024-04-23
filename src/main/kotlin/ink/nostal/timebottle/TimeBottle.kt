package ink.nostal.timebottle

import com.github.shynixn.mccoroutine.folia.SuspendingJavaPlugin
import ink.nostal.timebottle.utils.isFolia
import ink.nostal.timebottle.utils.isPaper
import ink.nostal.timebottle.utils.isSupportedFork
import ink.nostal.timebottle.utils.isSupportedVersion
import org.bukkit.Bukkit
import org.bukkit.Server
import org.bukkit.command.CommandSender
import org.incendo.cloud.execution.ExecutionCoordinator
import org.incendo.cloud.paper.PaperCommandManager

lateinit var plugin: SuspendingJavaPlugin
lateinit var platform: Server
lateinit var commandManager: PaperCommandManager<CommandSender>
var disabled = true

@Suppress("UNUSED")
class TimeBottle : SuspendingJavaPlugin() {

    override suspend fun onEnableAsync() {
        plugin = this
        platform = Bukkit.getServer()

        if (!isPaper) {
            logger.severe("This plugin can only run on Paper or its forks.")
            logger.severe("Paper provides better performance and expansive API on modern Minecraft version.")
            logger.severe("It's highly recommend to use Paper.")
            logger.severe("Current platform version: ${platform.version}")
            return
        }

        if (!isSupportedVersion) {
            logger.severe("This plugin can only run on 1.20 and above.")
            logger.severe("You are using an unsupported version, please update your Minecraft.")
            logger.severe("Current Minecraft version: ${platform.minecraftVersion}")
            return
        }

        if (isFolia) {
            logger.info("Detected Folia, the plugin will enable support for it.")
        }

        if (!isSupportedFork) {
            logger.warning("You are using an unsupported Paper fork.")
            logger.warning("The plugin can still run, but we won't provide any support.")
        }

        commandManager = PaperCommandManager.createNative(
            this,
            ExecutionCoordinator.asyncCoordinator()
        )

        disabled = false
    }

    override suspend fun onDisableAsync() {
        disabled = true
    }

}
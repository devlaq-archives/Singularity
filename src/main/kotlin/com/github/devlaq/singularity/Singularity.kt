package com.github.devlaq.singularity

import com.github.devlaq.singularity.command.GameCommand
import com.github.devlaq.singularity.event.PlayerEventHandler
import com.github.devlaq.singularity.test.TestGame
import io.github.monun.kommand.kommand
import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class Singularity: JavaPlugin() {

    companion object {
        var instance: Singularity? = null
            private set
    }

    override fun onEnable() {
        instance = this

        logger.info("Enabling ${description.name} ${description.version}")

        registerEvent(PlayerEventHandler())

        kommand {
            register("game") {
                GameCommand.dispatch(this)
            }
        }
        Games.registerGameFactory("test", TestGame.FactoryImpl())
    }

    override fun onDisable() {
        logger.info("Disabling ${description.name} ${description.version}")

        instance = null
    }

    fun registerEvent(listener: Listener) {
        Bukkit.getPluginManager().registerEvents(listener, this)
    }

}
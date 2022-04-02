package com.github.devlaq.matchmaker

import com.github.devlaq.matchmaker.command.MatchMakerCommand
import io.github.monun.kommand.kommand
import org.bukkit.plugin.java.JavaPlugin

class Main: JavaPlugin() {

    override fun onEnable() {
        kommand {
            register("matchmaker") {
                MatchMakerCommand.register(this)
            }
        }
    }

}
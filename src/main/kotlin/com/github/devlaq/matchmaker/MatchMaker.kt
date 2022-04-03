package com.github.devlaq.matchmaker

import com.github.devlaq.matchmaker.command.MatchMakerCommand
import com.github.devlaq.matchmaker.game.GameQueue
import io.github.monun.kommand.kommand
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class MatchMaker: JavaPlugin() {

    companion object {
        fun matchMake(gameName: String, vararg players: Player) {
             players.forEach { matchMake(gameName, it) }
        }

        fun matchMake(gameName: String, player: Player) {

        }
    }

    override fun onEnable() {
        kommand {
            register("matchmaker") {
                MatchMakerCommand.register(this)

            }
        }
    }

}
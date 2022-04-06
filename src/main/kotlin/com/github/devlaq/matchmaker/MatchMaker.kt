package com.github.devlaq.matchmaker

import com.github.devlaq.matchmaker.command.MatchMakerCommand
import io.github.monun.kommand.kommand
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class MatchMaker: JavaPlugin() {

    companion object {

        /*
           matchMake structures

           matchMake -> find available games from Games
           if no game available -> create game
           create game -> GameFactory#createGame

         */

        fun matchMake(gameName: String, vararg players: Player) {
            Games.matchmake(gameName, *players)
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
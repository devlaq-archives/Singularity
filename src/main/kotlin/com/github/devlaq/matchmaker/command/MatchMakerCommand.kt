package com.github.devlaq.matchmaker.command

import com.github.devlaq.matchmaker.Games
import io.github.monun.kommand.node.LiteralNode
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.entity.Player
import java.util.*

object MatchMakerCommand {

    fun register(init: LiteralNode) {
        init.apply {
            then("join") {
                requires { isPlayer && player.hasPermission("matchmaker.join") }
                then("gameType" to string()) {
                    executes {
                        player
                    }
                    then("players" to players()) {
                        requires { player.hasPermission("matchmaker.join.others") }
                    }
                }
            }
            then("joinid") {
                requires { isPlayer && player.hasPermission("matchmaker.joinid") }
                then("gameId" to string()) {
                    executes { context ->
                        val gameId: String = context["gameId"]
                        val game = Games.getGame(UUID.fromString(gameId))
                        if(game == null) {
                            feedback(Component.text("Error: Game with game uuid $gameId not found."))
                            return@executes
                        }
                        game.playerJoin(player)

                     }
                    then("players" to players()) {
                        requires { player.hasPermission("matchmaker.joinid.others") }
                        executes { context ->
                            val gameId: String = context["gameId"]
                            val players: Collection<Player> = context["players"]
                            val game = Games.getGame(UUID.fromString(gameId))
                            if(game == null) {
                                feedback(Component.text("Error: Game with game uuid $gameId not found."))
                                return@executes
                            }
                            if(game.remainingUsers() < players.size) {
                                feedback(Component.text("Error: input players are more than acceptable players of the game."))
                            }
                            players.forEach { game.playerJoin(it) }
                        }
                    }
                }
            }
            then("quit") {
                requires { player.hasPermission("matchmaker.quit") }
                executes {
                    val game = Games.findGame { it.players().contains(player) }.first()
                    game.playerQuit(player)
                }
                then("players" to players()) {
                    requires { player.hasPermission("matchmaker.quit.others") }
                    executes {
                        Games.findGame { it.players().contains(player) }.forEach {
                            it.playerQuit(player)
                        }
                    }
                }
            }
            then("kill") {
                requires { player.hasPermission("matchmaker.killgame") }
            }
        }
    }

}
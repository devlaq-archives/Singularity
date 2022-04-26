package com.github.devlaq.singularity.command

import com.github.devlaq.singularity.Games
import com.github.devlaq.singularity.data.DataManager
import com.github.devlaq.singularity.game.Game
import io.github.monun.kommand.getValue
import io.github.monun.kommand.node.LiteralNode
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object GameCommand {

    fun sendGameInfo(sender: CommandSender, game: Game) {
        sender.sendMessage("" +
                "Game Info ${game.id()}:\n" +
                "-Type: ${game.type()}\n" +
                "-Players: ${game.players()}/${game.acceptable()}\n" +
                "-Status: ${game.status()}")
    }

    fun dispatch(node: LiteralNode) {
        node.apply {
            then("matchmake") {
                then("factoryName" to string().apply { suggests { suggest(Games.getGameFactories().keys) } }) {
                    executes { context ->
                        val factoryName: String by context

                        val factory = Games.getGameFactory(factoryName)
                        if(factory == null) {
                            player.sendMessage("Can't find game type \"${factoryName}\"")
                            return@executes
                        }

                        Games.matchmake(factory, player)
                    }
                    then("players" to players()) {
                        executes { context ->
                            val factoryName: String by context
                            val players: Collection<Player> by context

                            val factory = Games.getGameFactory(factoryName)
                            if(factory == null) {
                                player.sendMessage("Can't find game type \"${factoryName}\"")
                                return@executes
                            }

                            Games.matchmake(factory, *players.toTypedArray())
                        }
                    }
                }
            }
            then("join") {
                then("gameId" to string()) {
                    executes { context ->
                        val gameId: String by context

                        val game = Games.getGame(gameId)
                        if(game == null) {
                            player.sendMessage("Can't find game id $gameId")
                            return@executes
                        } else if(!game.canJoin()) {
                            player.sendMessage("You can't join the game!")
                        }
                    }
                    then("players" to players()) {
                        executes { context ->
                            val gameId: String by context
                            val players: Collection<Player> by context

                            val game = Games.getGame(gameId)
                            if(game == null) {
                                player.sendMessage("Can't find game id $gameId")
                                return@executes
                            } else if(game.acceptable() < players.size) {
                                player.sendMessage("Too many players! (Acceptable ${game.acceptable()} < To join ${players.size}")
                                return@executes
                            }
                        }
                    }
                }
            }
            then("quit") {
                executes { context ->
                    val result = Games.findGame { it.players().contains(player) }
                    if(result.isEmpty()) {
                        player.sendMessage("You are not in a game!")
                        return@executes
                    }
                    val game = result.first()
                    game.playerQuit(player)
                }
            }
            then("info") {
                then("current") {
                    executes { context ->
                        val game = DataManager.user.get(player)?.currentGame
                        if(game == null) {
                            player.sendMessage("You are not in a game!")
                            return@executes
                        }
                        sendGameInfo(player, game)
                    }
                }
            }
            then("games") {
                executes { context ->

                }
                then("filters" to string()) {
                }
            }
        }
    }

}
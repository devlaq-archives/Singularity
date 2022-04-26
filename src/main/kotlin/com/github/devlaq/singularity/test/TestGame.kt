package com.github.devlaq.singularity.test

import com.github.devlaq.singularity.game.Game
import com.github.devlaq.singularity.game.GameFactory
import com.github.devlaq.singularity.game.GameStatus
import org.bukkit.entity.Player
import org.slf4j.LoggerFactory

object TestGame {

    class GameImpl(val id: String): Game() {

        val maxPlayers = 2

        private val logger = LoggerFactory.getLogger(this.javaClass)

        private val players = mutableListOf<Player>()
        private val status = GameStatus.Waiting

        override fun type(): String {
            return "test"
        }

        override fun id(): String {
            return id
        }

        override fun initialize() {
            logger.info("Initializing game!")
        }

        override fun start() {
            logger.info("Starting game!")
        }

        override fun stop() {
            logger.info("Stopping game!")
        }

        override fun playerJoin(player: Player) {
            players.add(player)
            players.forEach {
                it.sendMessage("${player.name} joined!")
            }
        }

        override fun playerQuit(player: Player) {
            players.remove(player)
            players.forEach {
                it.sendMessage("${player.name} quit!")
            }
        }

        override fun players(): Collection<Player> {
            return players
        }

        override fun canJoin(): Boolean {
            return players.size < maxPlayers
        }

        override fun acceptable(): Int {
            return maxPlayers - players.size
        }

        override fun status(): GameStatus {
            return status
        }

    }

    class FactoryImpl: GameFactory() {

        override fun type(): String {
            return "test"
        }

        override fun createGame(id: String): Game {
            return GameImpl(id)
        }

    }

}
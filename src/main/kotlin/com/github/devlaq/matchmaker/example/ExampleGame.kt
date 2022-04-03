package com.github.devlaq.matchmaker.example

import com.github.devlaq.matchmaker.game.Game
import com.github.devlaq.matchmaker.game.GameStatus
import org.slf4j.LoggerFactory

class ExampleGame: Game() {

    private val logger = LoggerFactory.getLogger("ExampleGame")

    private val status = GameStatus.Idle

    override fun initialize() {
        logger.info("Initializing example game!")
    }

    override fun start() {
        logger.info("Game started!")
    }

    override fun stop() {
        logger.info("Game stopped!")
    }

    override fun status(): GameStatus {
        return status
    }
}
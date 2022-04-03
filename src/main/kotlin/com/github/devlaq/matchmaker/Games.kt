package com.github.devlaq.matchmaker

import com.github.devlaq.matchmaker.game.GameFactory
import com.github.devlaq.matchmaker.game.GameQueue

object Games {

    private val games = mutableMapOf<String, GameFactory>()

    fun registerGame(name: String, factory: GameFactory) {
        require(games[name] == null) { "Game $name already registered!" }
        games[name] = factory
    }

    fun removeGame(name: String): GameFactory? {
        return games.remove(name)
    }

    fun getGame(name: String): GameFactory? {
        return games[name]
    }

    fun makeNewGame(name: String) {
        requireNotNull(games[name]) { "Game $name not found!" }

        val factory = games[name]
        factory?.createQueue()
    }

}
package com.github.devlaq.singularity

import com.github.devlaq.singularity.data.DataManager
import com.github.devlaq.singularity.game.Game
import com.github.devlaq.singularity.game.GameFactory
import org.bukkit.entity.Player
import kotlin.random.Random

object Games {

    private val gameFactories = mutableMapOf<String, GameFactory>()
    private val games = mutableMapOf<String, Game>()

    fun registerGameFactory(name: String, factory: GameFactory) {
        require(gameFactories[name] == null) { "Game type $name already registered!" }
        gameFactories[name] = factory
    }

    fun removeGameFactory(name: String): GameFactory? {
        return gameFactories.remove(name)
    }

    fun getGameFactory(name: String): GameFactory? {
        return gameFactories[name]
    }

    fun getGameFactories(): MutableMap<String, GameFactory> {
        return gameFactories
    }

    fun getGame(uid: String): Game? {
        return games[uid]
    }

    fun createGame(factory: GameFactory): Game {
        val id = Random.nextInt(0, 999999).toString()
        val game = factory.createGame(id)
        games[id] = game
        return game
    }

    fun findGame(filter: (game: Game) -> Boolean): Collection<Game> {
        return games.filter { filter(it.value) }.values
    }

    fun matchmake(factory: GameFactory, vararg player: Player) {
        player.forEach {
            DataManager.user.get(it)?.currentGame?.playerQuit(it)
        }
        val games = findGame { it.type() == factory.type() && it.canJoin() && it.acceptable() >= player.size }
        val game =
            if(games.isEmpty()) createGame(factory)
            else games.first()
        player.forEach { game.playerJoin(it) }
    }

}
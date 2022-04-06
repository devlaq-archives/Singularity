package com.github.devlaq.matchmaker

import com.github.devlaq.matchmaker.game.Game
import com.github.devlaq.matchmaker.game.GameFactory
import org.bukkit.entity.Player
import java.util.UUID

object Games {

    private val gameTypes = mutableMapOf<String, GameFactory>()
    private val games = mutableMapOf<UUID, Game>()

    fun registerGameFactory(name: String, factory: GameFactory) {
        require(gameTypes[name] == null) { "Game type $name already registered!" }
        gameTypes[name] = factory
    }

    fun removeGameFactory(name: String): GameFactory? {
        return gameTypes.remove(name)
    }

    fun getGameFactory(name: String): GameFactory? {
        return gameTypes[name]
    }

    fun getGame(uuid: UUID): Game? {
        return games[uuid]
    }

    fun createGame(factory: GameFactory): Game {
        return factory.createGame()
    }

    fun addGame(game: Game) {
        games[UUID.randomUUID()] = game
    }

    fun findGame(filter: (game: Game) -> Boolean): Collection<Game> {
        return games.filter { filter(it.value) }.values
    }

    fun matchmake(type: String, vararg player: Player) {
        val game = findGame { it.type() == type && it.canJoin() && it.remainingUsers() >= player.size }.first()
        player.forEach { game.playerJoin(it) }
    }

}
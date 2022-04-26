package com.github.devlaq.singularity.game

import org.bukkit.entity.Player

/**
 * @since 1.0-dev1
 * @author devlaq
 */
abstract class Game() {

    /**
     * Type of game.
     */
    abstract fun type(): String

    /**
     * Id of game.
     */
    abstract fun id(): String

    /**
     * Called when initializing game.
     */
    abstract fun initialize()

    /**
     * Called when starting game.
     */
    abstract fun start()

    /**
     * Called when game force stop.
     */
    abstract fun stop()

    /**
     * Called when new player joined to the game.
     */
    abstract fun playerJoin(player: Player)

    /**
     * Called when player quit the game.
     */
    abstract fun playerQuit(player: Player)

    /**
     * @return players
     */
    abstract fun players(): Collection<Player>

    /**
     * @return can player join the game.
     */
    abstract fun canJoin(): Boolean

    /**
     * acceptable users.
     *
     * @return acceptable users.
     */
    abstract fun acceptable(): Int

    /**
     * @return current game status.
     */
    abstract fun status(): GameStatus

}
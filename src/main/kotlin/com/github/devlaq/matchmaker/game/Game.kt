package com.github.devlaq.matchmaker.game

import org.bukkit.entity.Player

/**
 * @since 1.0-dev1
 * @author devlaq
 */
abstract class Game() {

    abstract fun type(): String

    abstract fun initialize()

    abstract fun start()

    /**
     * Called when game force stop.
     */
    abstract fun stop()

    abstract fun playerJoin(player: Player)

    abstract fun playerQuit(player: Player)

    abstract fun players(): Collection<Player>

    /**
     * @return can player join the game.
     */
    abstract fun canJoin(): Boolean

    /**
     * Remaining users to start.
     * this will used from party system.
     *
     * @return remaining usersto start.
     */
    abstract fun remainingUsers(): Int

    /**
     * @return current game status.
     */
    abstract fun status(): GameStatus

}
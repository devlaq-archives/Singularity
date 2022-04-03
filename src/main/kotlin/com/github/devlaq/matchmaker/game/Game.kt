package com.github.devlaq.matchmaker.game

abstract class Game {

    // Called at game initialize.
    abstract fun initialize()

    // Called at game start.
    abstract fun start()

    // Called at game force stop
    abstract fun stop()

    /**
     * @return Current game status.
     */
    abstract fun status(): GameStatus

}
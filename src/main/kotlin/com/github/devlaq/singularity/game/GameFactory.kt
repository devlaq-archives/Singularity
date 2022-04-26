package com.github.devlaq.singularity.game

abstract class GameFactory {

    abstract fun createGame(id: String): Game
    abstract fun type(): String

}
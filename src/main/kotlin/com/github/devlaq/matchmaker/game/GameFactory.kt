package com.github.devlaq.matchmaker.game

abstract class GameFactory {

    abstract fun createQueue(): GameQueue

}
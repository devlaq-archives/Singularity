package com.github.devlaq.matchmaker.example

import com.github.devlaq.matchmaker.game.GameFactory
import com.github.devlaq.matchmaker.game.GameQueue

class ExampleGameFactory: GameFactory() {

    override fun createQueue(): GameQueue {
        return ExampleGameQueue(0)
    }

}
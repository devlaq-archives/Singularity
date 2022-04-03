package com.github.devlaq.matchmaker

import com.github.devlaq.matchmaker.game.GameQueue

object Queues {

    private val queues = mutableMapOf<Int, GameQueue>()

    fun getQueue(id: Int): GameQueue? {
        return queues[id]
    }

    fun addQueue(queue: GameQueue) {
        require(queues[queue.id] == null) { "Queue with id ${queue.id} already exists!" }
        queues[queue.id] = queue
    }

    fun removeQueue(id: Int): GameQueue? {
        val queue = queues.remove(id)
        queue?.players()?.forEach { queue.playerQuit(it) }
        return queue
    }

    fun removeQueues(gameName: String) {
        queues.forEach { (k, v) ->
            if(v.type() == gameName) removeQueue(k)
        }
    }

    fun removeQueues(filter: (k: Int, v: GameQueue) -> Boolean) {
        queues.forEach { (k, v) ->
            if(filter(k, v)) removeQueue(k)
        }
    }

    fun clearQueues() {
        queues.forEach { (k, _) -> removeQueue(k) }
        queues.clear()
    }

    fun getRemainingQueues(): List<GameQueue> {
        return queues.values.filter { q -> q.remain() >= 1 }
    }

    fun getQueues(): MutableMap<Int, GameQueue> {
        return queues
    }

}
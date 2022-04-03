package com.github.devlaq.matchmaker.example

import com.github.devlaq.matchmaker.game.Game
import com.github.devlaq.matchmaker.game.GameQueue
import io.github.monun.heartbeat.coroutines.HeartbeatScope
import io.github.monun.heartbeat.coroutines.Suspension
import io.github.monun.heartbeat.coroutines.delayTick
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.bukkit.entity.Player

class ExampleGameQueue(id: Int) : GameQueue(id) {

    private val players = mutableListOf<Player>()

    private var gameStarting = false

    override fun type(): String {
        return "exampleGame"
    }

    override fun playerJoin(player: Player) {
        players.add(player)
        players.forEach {
            it.sendMessage("${it.name()} joined the game! (${players.size})")
        }

        if(players.size >= 4) {
            gameStarting = true
            HeartbeatScope().launch {
                val suspension = Suspension()
                val alerts = arrayOf(15, 10, 5, 4, 3, 2, 1)

                var time = 15
                repeat(15) {
                    suspension.delay(1000)
                    if(alerts.contains(time)) players.forEach { it.sendMessage("Game starts in $time seconds!") }
                    time--
                }
            }
        }
    }

    override fun playerQuit(player: Player) {
        players.remove(player)
        players.forEach {
            it.sendMessage("${it.name()} quit the game! (${players.size})")
        }

        if(players.size < 4) {
            gameStarting = false
            players.forEach {
                it.sendMessage("Not enough players!")
            }
        }
    }

    override fun players(): List<Player> {
        return players
    }

    override fun remain(): Int {
        TODO("Not yet implemented")
    }

}
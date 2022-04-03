package com.github.devlaq.matchmaker.game

import org.bukkit.entity.Player

abstract class GameQueue(val id: Int) {

    abstract fun type(): String
    abstract fun playerJoin(player: Player)
    abstract fun playerQuit(player: Player)
    abstract fun players(): List<Player>
    abstract fun remain(): Int

}
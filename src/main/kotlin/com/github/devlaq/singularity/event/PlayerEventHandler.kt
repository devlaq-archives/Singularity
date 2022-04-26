package com.github.devlaq.singularity.event

import com.github.devlaq.singularity.data.DataManager
import com.github.devlaq.singularity.data.UserDataManager
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class PlayerEventHandler: Listener {

    fun onJoin(event: PlayerJoinEvent) {
        if(!DataManager.user.has(event.player)) {
            DataManager.user.set(event.player, UserDataManager.User())
        }
    }

    fun onQuit(event: PlayerQuitEvent) {

    }

}
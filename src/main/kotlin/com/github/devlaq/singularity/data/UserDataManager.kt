package com.github.devlaq.singularity.data

import com.github.devlaq.singularity.game.Game
import org.bukkit.entity.Player

class UserDataManager : AbstractDataManager<Player, UserDataManager.User>() {

    private val map = mutableMapOf<Player, User>()

    override fun get(key: Player): User? {
        return map[key]
    }

    override fun set(key: Player, value: User) {
        map[key] = value
    }

    override fun has(key: Player): Boolean {
        return map[key] != null
    }

    override fun all(): Map<Player, User> {
        return map.toMap()
    }

    override fun key(): Collection<Player> {
        return map.keys
    }

    override fun value(): Collection<User> {
        return map.values
    }

    class User {
        var currentGame: Game? = null
    }

}

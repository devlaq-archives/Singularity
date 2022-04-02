package com.github.devlaq.matchmaker

object Games {

    private val games = mutableMapOf<String, Class<out Any>>()

    fun registerGame(name: String, clazz: Class<out Any>) {
        if(games[name] != null) throw RuntimeException("Game $name already registered!")
        games[name] = clazz
    }

}
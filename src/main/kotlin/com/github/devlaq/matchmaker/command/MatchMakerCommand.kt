package com.github.devlaq.matchmaker.command

import io.github.monun.kommand.node.LiteralNode

object MatchMakerCommand {

    fun register(init: LiteralNode) {
        init.apply {
            then("join") {
                then("gameType" to string()) {
                    executes {

                    }
                }
            }
            then("quit") {

            }
        }
    }

}
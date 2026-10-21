package com.javiviz.championshiptictactoe.features.players.data.dataSource

import com.javiviz.championshiptictactoe.app.enums.SymbolColors
import com.javiviz.championshiptictactoe.features.players.data.model.Player
import com.javiviz.championshiptictactoe.features.players.data.model.PlayerStatistics

class PlayersLocalDataSource {
    private val players = mutableListOf<Player>(
        Player(
            id = "1",
            apodo = "Majolotito",
            colorPreferido = SymbolColors.RED,
            statistics = PlayerStatistics(5,2,1,2)
        ),
        Player(
            id = "2",
            apodo = "Javiviz",
            colorPreferido = SymbolColors.PURPLE,
            statistics = PlayerStatistics(2,0,1,1)
        ),
        Player(
            id = "3",
            apodo = "MeliEli",
            colorPreferido = SymbolColors.RED,
            statistics = PlayerStatistics(3,1,1,1)
        )
    )

    fun getAllPlayers(): List<Player> {
        return players
    }

    fun getPlayerById(playerId: String): Player? {
        return players.find { it.id == playerId }
    }

    fun createPlayer(player: Player) : Player {
        players.add(player)
        return player
    }
    fun updatePlayer(player: Player) : Player {
        val index = players.indexOfFirst { it.id == player.id }
        if (index != -1) {
            players[index] = player
        }
        return player
    }

    fun deletePlayer(playerId: String) {
        players.removeIf { it.id == playerId }
    }

    fun createId() : String {
        val maxId = players.maxOfOrNull { it.id.toIntOrNull() ?: 0 } ?: 0
        return (maxId + 1).toString()
    }

    fun updateStatistics(playerId: String, state: String) {
        val index = players.indexOfFirst { it.id == playerId }
        players[index].statistics.totalMatches++

        when(state) {
            "win" -> players[index].statistics.wins++
            "draw" -> players[index].statistics.draws++
            "loss" -> players[index].statistics.losses++
        }
    }
}
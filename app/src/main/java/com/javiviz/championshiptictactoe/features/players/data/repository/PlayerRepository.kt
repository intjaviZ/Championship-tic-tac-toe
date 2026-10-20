package com.javiviz.championshiptictactoe.features.players.data.repository

import com.javiviz.championshiptictactoe.features.players.data.model.Player

interface PlayerRepository {
    fun getAllPlayers(): List<Player>
    fun getPlayerById(playerId: String): Player?
    fun createPlayer(player: Player) : Player
    fun updatePlayer(player: Player) : Player
    fun deletePlayer(playerId: String)

    fun getIdPlayer() : String
}
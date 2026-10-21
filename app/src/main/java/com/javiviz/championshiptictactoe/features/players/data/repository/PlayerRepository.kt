package com.javiviz.championshiptictactoe.features.players.data.repository

import com.javiviz.championshiptictactoe.app.enums.ResultadoPartida
import com.javiviz.championshiptictactoe.app.enums.SymbolColors
import com.javiviz.championshiptictactoe.features.players.data.model.Player
import kotlinx.coroutines.flow.Flow

interface PlayerRepository {
    fun observePlayers(): Flow<List<Player>>
    suspend fun getPlayerById(id: String): Player?
    suspend fun createPlayer(apodo: String, color: SymbolColors): Player
    suspend fun updatePlayer(player: Player)
    suspend fun deletePlayer(id: String)
    suspend fun updateStatistics(id: String, result: ResultadoPartida)
}
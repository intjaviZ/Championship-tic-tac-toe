package com.javiviz.championshiptictactoe.features.players.data.repository

import com.javiviz.championshiptictactoe.app.enums.ResultadoPartida
import com.javiviz.championshiptictactoe.app.enums.SymbolColors
import com.javiviz.championshiptictactoe.features.players.data.dataSource.PlayersDataStoreDataSource
import com.javiviz.championshiptictactoe.features.players.data.model.Player
import kotlinx.coroutines.flow.Flow

class PlayerRepositoryImplementation(
    private val dataSource: PlayersDataStoreDataSource
) : PlayerRepository {

    override fun observePlayers(): Flow<List<Player>> {
        return dataSource.players
    }
    override suspend fun getPlayerById(id: String): Player? {
        return dataSource.getPlayerById(id)
    }

    override suspend fun createPlayer(apodo: String, color: SymbolColors): Player {
        return dataSource.createPlayer(apodo, color)
    }

    override suspend fun updatePlayer(player: Player) {
        return dataSource.updatePlayer(player)
    }

    override suspend fun deletePlayer(id: String) {
        return dataSource.deletePlayer(id)
    }

    override suspend fun updateStatistics(id: String, result: ResultadoPartida) {
        return dataSource.updateStatistics(id, result)
    }
}
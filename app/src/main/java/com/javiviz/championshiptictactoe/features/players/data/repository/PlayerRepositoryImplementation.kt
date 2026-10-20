package com.javiviz.championshiptictactoe.features.players.data.repository

import com.javiviz.championshiptictactoe.features.players.data.dataSource.PlayersLocalDataSource
import com.javiviz.championshiptictactoe.features.players.data.model.Player

class PlayerRepositoryImplementation(
    private val dataSource: PlayersLocalDataSource
) : PlayerRepository {
    override fun getAllPlayers(): List<Player> {
        return dataSource.getAllPlayers()
    }

    override fun getPlayerById(playerId: String): Player? {
        return dataSource.getPlayerById(playerId)
    }

    override fun createPlayer(player: Player): Player {
        return dataSource.createPlayer(player)
    }

    override fun updatePlayer(player: Player): Player {
        return dataSource.updatePlayer(player)
    }

    override fun deletePlayer(playerId: String) {
        return dataSource.deletePlayer(playerId)
    }

    override fun getIdPlayer(): String {
        return dataSource.createId()
    }

}
package com.javiviz.championshiptictactoe.features.players.di

import com.javiviz.championshiptictactoe.features.players.data.dataSource.PlayersLocalDataSource
import com.javiviz.championshiptictactoe.features.players.data.repository.PlayerRepository
import com.javiviz.championshiptictactoe.features.players.data.repository.PlayerRepositoryImplementation
import com.javiviz.championshiptictactoe.features.players.presentation.viewModel.PlayersViewModelFactory

class PlayersModule {
    private val localDataSource: PlayersLocalDataSource by lazy {
        PlayersLocalDataSource()
    }

    val repository: PlayerRepository by lazy {
        PlayerRepositoryImplementation(localDataSource)
    }

    val factoryPlayers: PlayersViewModelFactory by lazy {
        PlayersViewModelFactory(repository)
    }
}
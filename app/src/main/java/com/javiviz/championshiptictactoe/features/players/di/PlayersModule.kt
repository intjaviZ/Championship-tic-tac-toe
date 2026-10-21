package com.javiviz.championshiptictactoe.features.players.di

import android.content.Context
import com.javiviz.championshiptictactoe.features.players.data.dataSource.PlayersDataStoreDataSource
import com.javiviz.championshiptictactoe.features.players.data.dataSource.playersDataStore
import com.javiviz.championshiptictactoe.features.players.data.repository.PlayerRepository
import com.javiviz.championshiptictactoe.features.players.data.repository.PlayerRepositoryImplementation
import com.javiviz.championshiptictactoe.features.players.presentation.viewModel.PlayersViewModelFactory

class PlayersModule(private val context: Context) {
    private val dataSource by lazy {
        PlayersDataStoreDataSource(context.playersDataStore)
    }
    val repository: PlayerRepository by lazy { PlayerRepositoryImplementation(dataSource) }
    val factoryPlayers by lazy { PlayersViewModelFactory(repository) }
}
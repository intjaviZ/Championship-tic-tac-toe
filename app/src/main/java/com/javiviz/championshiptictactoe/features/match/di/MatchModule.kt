package com.javiviz.championshiptictactoe.features.match.di

import com.javiviz.championshiptictactoe.features.match.data.dataSource.ActiveMatchStore
import com.javiviz.championshiptictactoe.features.match.presentation.viewModel.MatchViewModelFactory
import com.javiviz.championshiptictactoe.features.players.data.repository.PlayerRepository

class MatchModule(
    private val playerRepository: PlayerRepository
) {
    val activeMatchStore: ActiveMatchStore by lazy { ActiveMatchStore() }

    val matchFactory: MatchViewModelFactory by lazy {
        MatchViewModelFactory(playerRepository, activeMatchStore)
    }
}
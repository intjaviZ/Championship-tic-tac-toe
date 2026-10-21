package com.javiviz.championshiptictactoe.features.match.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.javiviz.championshiptictactoe.features.match.data.dataSource.ActiveMatchStore
import com.javiviz.championshiptictactoe.features.players.data.repository.PlayerRepository

class MatchViewModelFactory(
    private val playerRepository: PlayerRepository,
    private val activeMatchStore: ActiveMatchStore
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        MatchViewModel(playerRepository, activeMatchStore) as T
}
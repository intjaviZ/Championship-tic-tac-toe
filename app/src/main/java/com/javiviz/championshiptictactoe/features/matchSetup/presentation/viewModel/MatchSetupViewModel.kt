package com.javiviz.championshiptictactoe.features.matchSetup.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.javiviz.championshiptictactoe.features.matchSetup.presentation.screens.MatchSetupUIState
import com.javiviz.championshiptictactoe.features.players.data.model.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MatchSetupViewModel : ViewModel() {
    private val _matchSetupState = MutableStateFlow(MatchSetupUIState())
    val matchSetupState : StateFlow<MatchSetupUIState> = _matchSetupState.asStateFlow()

    fun updatePlayer1(player : Player) {
        _matchSetupState.update { it.copy(selectedPlayer1 = player) }
    }
    fun updatePlayer2(player : Player) {
        _matchSetupState.update { it.copy(selectedPlayer2 = player) }
    }
}
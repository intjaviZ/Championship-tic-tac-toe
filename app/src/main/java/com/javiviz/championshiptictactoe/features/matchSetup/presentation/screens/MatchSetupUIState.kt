package com.javiviz.championshiptictactoe.features.matchSetup.presentation.screens

import com.javiviz.championshiptictactoe.features.players.data.model.Player

data class MatchSetupUIState(
    val selectedPlayer1 : Player? = null,
    val selectedPlayer2 : Player? = null,
) {
    val canStart: Boolean
        get() = selectedPlayer1 != null &&
                selectedPlayer2 != null &&
                selectedPlayer1.id != selectedPlayer2.id
}

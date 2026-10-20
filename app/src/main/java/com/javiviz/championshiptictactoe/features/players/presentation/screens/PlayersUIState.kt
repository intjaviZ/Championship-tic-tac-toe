package com.javiviz.championshiptictactoe.features.players.presentation.screens

import com.javiviz.championshiptictactoe.features.players.data.model.Player

data class PlayersUIState(
    val players : List<Player> = emptyList(),
    val player : Player? = null
)

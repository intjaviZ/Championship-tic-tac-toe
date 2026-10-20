package com.javiviz.championshiptictactoe.features.players.presentation.screens

import com.javiviz.championshiptictactoe.app.enums.SymbolColors

data class CreatePlayerUIState (
    val apodo : String = "",
    val color : SymbolColors = SymbolColors.RED
)
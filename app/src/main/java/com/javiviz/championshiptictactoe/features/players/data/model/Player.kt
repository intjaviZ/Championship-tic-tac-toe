package com.javiviz.championshiptictactoe.features.players.data.model

import com.javiviz.championshiptictactoe.app.enums.SymbolColors
import kotlinx.serialization.Serializable

@Serializable
data class Player(
    val id : String,
    val apodo : String,
    val colorPreferido : SymbolColors,
    val statistics: PlayerStatistics

)

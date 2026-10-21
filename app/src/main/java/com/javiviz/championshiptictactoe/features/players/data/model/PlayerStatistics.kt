package com.javiviz.championshiptictactoe.features.players.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PlayerStatistics(
    val totalMatches: Int = 0,
    val wins: Int = 0,
    val draws: Int = 0,
    val losses: Int = 0
)


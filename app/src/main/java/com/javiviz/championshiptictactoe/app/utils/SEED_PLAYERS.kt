package com.javiviz.championshiptictactoe.app.utils

import com.javiviz.championshiptictactoe.app.enums.SymbolColors
import com.javiviz.championshiptictactoe.features.players.data.model.Player
import com.javiviz.championshiptictactoe.features.players.data.model.PlayerStatistics

val SEED_PLAYERS = listOf<Player>(
    Player("1", "Majolotito", SymbolColors.RED, PlayerStatistics(5, 2, 1, 2)),
    Player("2", "Javiviz", SymbolColors.PURPLE, PlayerStatistics(2, 0, 1, 1)),
    Player("3", "MeliEli", SymbolColors.RED, PlayerStatistics(3, 1, 1, 1))
)
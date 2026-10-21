package com.javiviz.championshiptictactoe.features.statistics.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.javiviz.championshiptictactoe.Championshiptictactoe
import com.javiviz.championshiptictactoe.features.players.presentation.viewModel.PlayersViewModel
import com.javiviz.championshiptictactoe.features.statistics.presentation.components.PositionCard

@Composable
fun StatisticsScreen(
    viewModel: PlayersViewModel = viewModel(
        factory = (LocalContext.current.applicationContext as Championshiptictactoe).playersModule.factoryPlayers
    )
) {
    val uiState by viewModel.playersState.collectAsStateWithLifecycle()

    Column() {
        uiState.players.forEach {
            PositionCard(
                apodo = it.apodo,
                pj = it.statistics.totalMatches,
                pg = it.statistics.wins,
                pe = it.statistics.draws,
                pp = it.statistics.losses
            )
        }
    }
}
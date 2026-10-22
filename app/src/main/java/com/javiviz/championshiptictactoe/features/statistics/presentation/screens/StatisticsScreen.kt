package com.javiviz.championshiptictactoe.features.statistics.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    LazyColumn (
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 8.dp
        )
    ) {
        item {
            Text(
                text = "CLASIFICACIÓN GENERAL",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                letterSpacing = 0.5.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        itemsIndexed(
            items = uiState.playersSortedByWins,
            key = { _, player -> player.id }
        ) { index, player ->
            PositionCard(
                rank = index + 1,
                apodo = player.apodo,
                playerColor = player.colorPreferido.composeColor,
                pj = player.statistics.totalMatches,
                pg = player.statistics.wins,
                pe = player.statistics.draws,
                pp = player.statistics.losses
            )
        }
    }
}
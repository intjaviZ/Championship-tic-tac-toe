package com.javiviz.championshiptictactoe.features.players.presentation.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.javiviz.championshiptictactoe.Championshiptictactoe
import com.javiviz.championshiptictactoe.features.players.presentation.viewModel.PlayersViewModel

@Composable
fun PlayersScreen(
    viewModel: PlayersViewModel = viewModel(
        factory = (LocalContext.current.applicationContext as Championshiptictactoe).playersModule.factoryPlayers
    )
) {
    val uiState by viewModel.playersState.collectAsStateWithLifecycle()
    LazyColumn() {
        items(uiState.players.size) { index ->
            Text(text = uiState.players[index].apodo)
        }
    }
}
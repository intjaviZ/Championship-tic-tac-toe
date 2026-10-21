package com.javiviz.championshiptictactoe.features.players.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.javiviz.championshiptictactoe.Championshiptictactoe
import com.javiviz.championshiptictactoe.features.players.presentation.components.SymbolColorDropdown
import com.javiviz.championshiptictactoe.features.players.presentation.viewModel.PlayersViewModel

@Composable
fun CreatePlayerScreen(
    viewModel: PlayersViewModel = viewModel(
        factory = (LocalContext.current.applicationContext as Championshiptictactoe).playersModule.factoryPlayers
    )
) {
    val createPlayerUIState by viewModel.createPlayerUIState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.padding(64.dp)
    ) {
        OutlinedTextField(
            value = createPlayerUIState.apodo,
            onValueChange = { viewModel.setApodo(it) }
        )
        SymbolColorDropdown(
            selectedColor = createPlayerUIState.color,
            onValueChange = { viewModel.setColor(it) }
        )
        Button(onClick = { viewModel.createPlayer() }) {
            Text(text = "Crear jugador")
        }
    }
}
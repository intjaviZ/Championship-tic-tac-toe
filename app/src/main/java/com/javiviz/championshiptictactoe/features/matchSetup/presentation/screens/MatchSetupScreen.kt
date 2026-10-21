package com.javiviz.championshiptictactoe.features.matchSetup.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.javiviz.championshiptictactoe.Championshiptictactoe
import com.javiviz.championshiptictactoe.app.enums.Sections
import com.javiviz.championshiptictactoe.features.main.presentation.MainViewModel
import com.javiviz.championshiptictactoe.features.match.presentation.viewModel.MatchViewModel
import com.javiviz.championshiptictactoe.features.matchSetup.presentation.components.PlayersDropdown
import com.javiviz.championshiptictactoe.features.matchSetup.presentation.viewModel.MatchSetupViewModel
import com.javiviz.championshiptictactoe.features.players.data.model.Player
import com.javiviz.championshiptictactoe.features.players.presentation.viewModel.PlayersViewModel

@Composable
fun MatchSetupScreen(
    onStartMatch: (Player, Player) -> Unit,
    matchSetupViewModel: MatchSetupViewModel = viewModel(),
    playerViewModel: PlayersViewModel = viewModel(
        factory = (LocalContext.current.applicationContext as Championshiptictactoe).playersModule.factoryPlayers
    )
) {
    val players by playerViewModel.playersState.collectAsStateWithLifecycle()
    val state by matchSetupViewModel.matchSetupState.collectAsStateWithLifecycle()

    Column {
        PlayersDropdown(
            players = players.players,
            selectedPlayer = state.selectedPlayer1,
            excludedPlayer = state.selectedPlayer2,
            onValueChange = matchSetupViewModel::updatePlayer1,
            label = "Jugador 1"
        )
        PlayersDropdown(
            players = players.players,
            selectedPlayer = state.selectedPlayer2,
            excludedPlayer = state.selectedPlayer1,
            onValueChange = matchSetupViewModel::updatePlayer2,
            label = "Jugador 2"
        )
        Button(
            enabled = state.canStart,
            onClick = {
                val p1 = state.selectedPlayer1
                val p2 = state.selectedPlayer2
                if (p1 != null && p2 != null) onStartMatch(p1, p2)
            }
        ) { Text("Iniciar partida") }
    }
}
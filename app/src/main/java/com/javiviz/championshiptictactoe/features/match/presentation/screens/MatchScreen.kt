package com.javiviz.championshiptictactoe.features.match.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.javiviz.championshiptictactoe.app.enums.Simbolos
import com.javiviz.championshiptictactoe.features.match.presentation.components.GatoBoard
import com.javiviz.championshiptictactoe.features.match.presentation.viewModel.MatchViewModel

@Composable
fun MatchScreen(matchViewModel : MatchViewModel) {
    val matchState by matchViewModel.matchState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.padding(32.dp)) {
        Text("Turno: ${matchViewModel.turnMatch()}")
        GatoBoard(
            matchState.tablero,
            onClick = { x, y -> matchViewModel.takeTurn(x, y) }
        )
        Row() {
            Button(onClick = { matchViewModel.resetGame() }) {
                Text("Reiniciar")
            }
            Button(onClick = { matchViewModel.startGame() }) {
                Text("Jugar")
            }
        }
        if (matchState.ganador != null) {
            if (matchState.ganador == Simbolos.VACIA) {
                Text("Empate")
            } else {
                Text("Ganador: ${matchState.ganador}")
            }
        }
    }
}

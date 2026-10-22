package com.javiviz.championshiptictactoe.features.match.presentation.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.javiviz.championshiptictactoe.app.enums.EstadoJuego
import com.javiviz.championshiptictactoe.app.enums.Simbolos
import com.javiviz.championshiptictactoe.features.match.presentation.components.GatoBoard
import com.javiviz.championshiptictactoe.features.match.presentation.components.MatchHeader
import com.javiviz.championshiptictactoe.features.match.presentation.components.MatchTopBar
import com.javiviz.championshiptictactoe.features.match.presentation.viewModel.MatchViewModel

@Composable
fun MatchScreen(
    matchViewModel : MatchViewModel,
    onExit: () -> Unit = {}
) {
    val state by matchViewModel.matchState.collectAsStateWithLifecycle()
    var showExitDialog by remember { mutableStateOf(false) }

    val enCurso = state.estadoPartida == EstadoJuego.JUGANDO
    val terminada = state.estadoPartida == EstadoJuego.FINALIZADO
    val hayJugadas = state.jugadas > 0

    // Si hay una partida a medias, confirma antes de salir
    val intentarSalir = { if (enCurso && hayJugadas) showExitDialog = true else onExit() }
    BackHandler (onBack = intentarSalir)

    val (titulo, subtitulo) = when {
        terminada && state.ganador == Simbolos.VACIA ->
            "¡Empate!" to "Nadie gana esta ronda. Reinicia el tablero para otra."
        terminada ->
            "¡Ganó ${state.jugadorGanador?.apodo.orEmpty()}!" to "Reinicia el tablero para jugar otra ronda."
        else ->
            "Tu jugada, ${state.jugadorActual?.apodo.orEmpty()}" to "Toca una casilla y busca tus tres en línea."
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MatchTopBar(
            onBack = intentarSalir,
            onReset = matchViewModel::resetGame,
            resetEnabled = hayJugadas
        )
        Spacer(Modifier.height(16.dp))
        MatchHeader(state)
        Spacer(Modifier.height(24.dp))

        Text(titulo, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Text(
            subtitulo,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 4.dp)
        )
        Spacer(Modifier.height(24.dp))

        GatoBoard(
            tablero = state.tablero,
            colorO = state.colorJ1.composeColor,
            colorX = state.colorJ2.composeColor,
            habilitado = enCurso,
            lineaGanadora = state.lineaGanadora,
            onClick = matchViewModel::takeTurn
        )

        Row(
            modifier = Modifier.padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.Group, null,
                modifier = Modifier.size(16.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.width(6.dp))
            Text(
                "${state.jugadas} jugadas · ${state.casillasLibres} casillas libres",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Spacer(Modifier.height(24.dp))

        // Cuando termina la ronda, el botón se resalta
        OutlinedButton (
            onClick = matchViewModel::resetGame,
            enabled = hayJugadas,
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = if (terminada) MaterialTheme.colorScheme.primary else Color.Transparent,
                contentColor = if (terminada) MaterialTheme.colorScheme.onPrimary
                else MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.fillMaxWidth().height(56.dp)
        ) {
            Icon(Icons.Default.Refresh, null)
            Spacer(Modifier.width(8.dp))
            Text("Reiniciar Tablero", fontWeight = FontWeight.SemiBold)
        }
        TextButton (onClick = intentarSalir, modifier = Modifier.padding(top = 8.dp)) {
            Text("Abandonar / Salir", fontWeight = FontWeight.SemiBold)
        }
    }

    if (showExitDialog) {
        AlertDialog(
            onDismissRequest = { showExitDialog = false },
            title = { Text("¿Abandonar la partida?") },
            text = { Text("Se perderá esta ronda y no se registrará ningún resultado.") },
            confirmButton = {
                TextButton(onClick = { showExitDialog = false; onExit() }) { Text("Abandonar") }
            },
            dismissButton = {
                TextButton(onClick = { showExitDialog = false }) { Text("Seguir jugando") }
            }
        )
    }
}

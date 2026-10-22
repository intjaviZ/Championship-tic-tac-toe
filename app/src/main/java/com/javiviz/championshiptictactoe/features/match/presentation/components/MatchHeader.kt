package com.javiviz.championshiptictactoe.features.match.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.javiviz.championshiptictactoe.app.enums.EstadoJuego
import com.javiviz.championshiptictactoe.app.enums.Simbolos
import com.javiviz.championshiptictactoe.features.match.presentation.screens.MatchUIState

@Composable
fun MatchHeader(state: MatchUIState) {
    val enCurso = state.estadoPartida == EstadoJuego.JUGANDO
    val gana1 = state.ganador == Simbolos.O
    val gana2 = state.ganador == Simbolos.X

    val sub1 = when {
        gana1 -> "¡Ganador!"
        enCurso && state.turnoJugador1 -> "Tu turno"
        else -> state.colorJ1.label
    }
    val sub2 = when {
        gana2 -> "¡Ganador!"
        enCurso && !state.turnoJugador1 -> "Tu turno"
        else -> state.colorJ2.label + if (state.colorJ2AlAzar) " · al azar" else ""
    }

    Surface (
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row (Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            PlayerChip(
                nombre = state.jugador1?.apodo.orEmpty(),
                simbolo = Simbolos.O,
                color = state.colorJ1.composeColor,
                subtitulo = sub1,
                activo = gana1 || (enCurso && state.turnoJugador1),
                modifier = Modifier.weight(1f)
            )
            Surface(shape = RoundedCornerShape(50), color = MaterialTheme.colorScheme.surfaceVariant) {
                Text(
                    "Ronda ${state.ronda}",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }
            PlayerChip(
                nombre = state.jugador2?.apodo.orEmpty(),
                simbolo = Simbolos.X,
                color = state.colorJ2.composeColor,
                subtitulo = sub2,
                activo = gana2 || (enCurso && !state.turnoJugador1),
                modifier = Modifier.weight(1f)
            )
        }
    }
}
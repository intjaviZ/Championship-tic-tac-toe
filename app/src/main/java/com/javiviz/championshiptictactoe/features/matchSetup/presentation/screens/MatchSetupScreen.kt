package com.javiviz.championshiptictactoe.features.matchSetup.presentation.screens

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.javiviz.championshiptictactoe.Championshiptictactoe
import com.javiviz.championshiptictactoe.features.matchSetup.presentation.components.PlayerCard
import com.javiviz.championshiptictactoe.features.matchSetup.presentation.components.PlayersDropdown
import com.javiviz.championshiptictactoe.features.matchSetup.presentation.components.VsDivider
import com.javiviz.championshiptictactoe.features.matchSetup.presentation.viewModel.MatchSetupViewModel
import com.javiviz.championshiptictactoe.features.players.data.model.Player
import com.javiviz.championshiptictactoe.features.players.presentation.viewModel.PlayersViewModel

@Composable
fun MatchSetupScreen(
    onStartMatch: (Player, Player) -> Unit,
    onCreatePlayer: () -> Unit = {},
    matchSetupViewModel: MatchSetupViewModel = viewModel(),
    playerViewModel: PlayersViewModel = viewModel(
        factory = (LocalContext.current.applicationContext as Championshiptictactoe).playersModule.factoryPlayers
    )
) {
    val players by playerViewModel.playersState.collectAsStateWithLifecycle()
    val state by matchSetupViewModel.matchSetupState.collectAsStateWithLifecycle()

    val p1 = state.selectedPlayer1
    val p2 = state.selectedPlayer2
    val colorRepetido = p1 != null && p2 != null && p1.colorPreferido == p2.colorPreferido

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        // Encabezado
        Row (verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Default.SportsEsports, contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(16.dp)
            )
            Spacer(Modifier.width(6.dp))
            Text(
                "MULTIJUGADOR LOCAL",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Text(
            "La próxima jugada\nes tuya.",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            "Elige tu rival. Comparte pantalla. ¡A jugar!",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
        )

        // Jugador 1
        PlayerCard(title = "Jugador 1 (Símbolo O)", selectedColor = p1?.colorPreferido) {
            PlayersDropdown(
                players = players.players,
                selectedPlayer = p1,
                excludedPlayer = p2,
                onValueChange = matchSetupViewModel::updatePlayer1,
                label = "Jugador 1"
            )
        }

        VsDivider()

        // Jugador 2
        PlayerCard (title = "Jugador 2 (Símbolo X)", selectedColor = p2?.colorPreferido) {
            PlayersDropdown(
                players = players.players,
                selectedPlayer = p2,
                excludedPlayer = p1,
                onValueChange = matchSetupViewModel::updatePlayer2,
                label = "Jugador 2"
            )
            if (colorRepetido) {
                Spacer(Modifier.height(12.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFFFF4D6))
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Shuffle, null, tint = Color(0xFF7A5C00), modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "¡Color repetido! Se asignará otro color al azar para esta partida",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF7A5C00)
                    )
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        // Acciones
        Button(
            enabled = state.canStart,
            onClick = { if (p1 != null && p2 != null) onStartMatch(p1, p2) },
            shape = RoundedCornerShape(50),
            modifier = Modifier.fillMaxWidth().height(56.dp)
        ) {
            Icon(Icons.Default.PlayArrow, null)
            Spacer(Modifier.width(8.dp))
            Text("Iniciar Partida", fontWeight = FontWeight.SemiBold)
        }
        Spacer(Modifier.height(12.dp))
        OutlinedButton (
            onClick = onCreatePlayer,
            shape = RoundedCornerShape(50),
            modifier = Modifier.fillMaxWidth().height(56.dp)
        ) {
            Icon(Icons.Default.PersonAdd, null)
            Spacer(Modifier.width(8.dp))
            Text("+ Crear Nuevo Jugador", fontWeight = FontWeight.SemiBold)
        }
    }
}
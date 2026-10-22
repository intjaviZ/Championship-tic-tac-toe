package com.javiviz.championshiptictactoe.features.match.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun MatchTopBar(onBack: () -> Unit, onReset: () -> Unit, resetEnabled: Boolean) {
    Row (Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        IconButton (onClick = onBack) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
        }
        Text(
            "Partida en Curso",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f).padding(start = 8.dp)
        )
        FilledTonalIconButton (onClick = onReset, enabled = resetEnabled) {
            Icon(Icons.Default.Refresh, contentDescription = "Reiniciar tablero")
        }
    }
}
package com.javiviz.championshiptictactoe.features.match.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.javiviz.championshiptictactoe.app.enums.Simbolos

@Composable
fun SquareButton(
    simbolo: Simbolos,
    color: Color,
    enabled: Boolean,
    ganadora: Boolean,
    atenuada: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val vacia = simbolo == Simbolos.VACIA
    val forma = RoundedCornerShape(16.dp)
    val fondo = if (vacia) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f)
    else color.copy(alpha = if (ganadora) 0.30f else 0.15f)

    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clip(forma)
            .background(fondo)
            .then(if (ganadora) Modifier.border(2.dp, color, forma) else Modifier)
            .alpha(if (atenuada) 0.45f else 1f)
            .clickable(enabled = enabled, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        if (vacia) {
            Box(
                Modifier
                    .size(6.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f))
            )
        } else {
            SymbolMark(simbolo, color, Modifier.fillMaxSize(), animated = true)
        }
    }
}
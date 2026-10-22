package com.javiviz.championshiptictactoe.features.match.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.javiviz.championshiptictactoe.app.enums.Simbolos

@Composable
fun PlayerChip(
    nombre: String,
    simbolo: Simbolos,
    color: Color,
    subtitulo: String,
    activo: Boolean,
    modifier: Modifier = Modifier
) {
    val borde by animateColorAsState(if (activo) color else Color.Transparent, label = "borde")
    val forma = RoundedCornerShape(16.dp)
    Column (
        modifier = modifier
            .clip(forma)
            .border(2.dp, borde, forma)
            .padding(vertical = 12.dp, horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SymbolMark(simbolo, color, Modifier.size(32.dp))
        Spacer(Modifier.height(6.dp))
        Text(
            "$nombre (${simbolo.label})",
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(subtitulo, style = MaterialTheme.typography.labelSmall, color = color)
    }
}
package com.javiviz.championshiptictactoe.features.statistics.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.EmojiEvents
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.javiviz.championshiptictactoe.app.enums.SymbolColors

@Composable
fun PositionCard(
    rank : Int,
    apodo : String,
    playerColor : Color,
    pj: Int,
    pg : Int,
    pe : Int,
    pp : Int
) {
    val isFirstPlace = rank == 1

    val highlightColor = Color(0xFF6200EA) // Morado principal
    val borderColor = if (isFirstPlace) highlightColor.copy(alpha = 0.5f) else Color.LightGray
    val rankColor = if (isFirstPlace) highlightColor else Color.Gray

    OutlinedCard (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, borderColor),
        /*colors = CardDefaults.outlinedCardColors(containerColor = Color.White)*/
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Fila superior: Posición, color, apodo y trofeo (si es #1)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "#$rank",
                    fontWeight = FontWeight.ExtraBold,
                    color = rankColor,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.width(12.dp))

                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(playerColor)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = apodo,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.weight(1f))

                if (isFirstPlace) {
                    Icon(
                        imageVector = Icons.Rounded.EmojiEvents,
                        contentDescription = "Primer lugar",
                        tint = Color(0xFFD4AF37) // Dorado
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                StatPill(text = "PJ: $pj", isHighlighted = false, modifier = Modifier.weight(1f))
                StatPill(text = "PG: $pg", isHighlighted = true, modifier = Modifier.weight(1f))
                StatPill(text = "PE: $pe", isHighlighted = false, modifier = Modifier.weight(1f))
                StatPill(text = "PP: $pp", isHighlighted = false, modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun StatPill(
    text: String,
    isHighlighted: Boolean,
    modifier: Modifier = Modifier
) {
    // Si es PG (highlighted), usa fondo morado claro y texto morado oscuro
    val containerColor = if (isHighlighted) Color(0xFFF3E8FF) else Color(0xFFF3F4F6)
    val textColor = if (isHighlighted) Color(0xFF7E22CE) else Color.Gray

    Surface (
        color = containerColor,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 6.dp)
        )
    }
}
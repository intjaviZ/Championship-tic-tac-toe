package com.javiviz.championshiptictactoe.features.players.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.javiviz.championshiptictactoe.app.enums.SymbolColors

@Composable
fun ColorPicker(
    selected: SymbolColors,
    onSelect: (SymbolColors) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        SymbolColors.entries.chunked(4).forEach { row ->
            Row (modifier = Modifier.fillMaxWidth()) {
                row.forEach { option ->
                    val isSelected = option == selected
                    val color = option.composeColor

                    Column (
                        modifier = Modifier
                            .weight(1f)
                            .clickable { onSelect(option) },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .then(
                                    if (isSelected) Modifier.border(2.dp, color, CircleShape)
                                    else Modifier
                                )
                                .padding(if (isSelected) 4.dp else 0.dp)
                                .clip(CircleShape)
                                .background(color),
                            contentAlignment = Alignment.Center
                        ) {
                            if (isSelected) {
                                Icon(Icons.Default.Check, contentDescription = null, tint = Color.White)
                            }
                        }
                        Text(
                            text = option.label,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSelected) color else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(top = 6.dp)
                        )
                    }
                }
            }
        }
    }
}
package com.javiviz.championshiptictactoe.features.matchSetup.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.javiviz.championshiptictactoe.app.enums.SymbolColors

@Composable
fun ColorDots(selected: SymbolColors?) {
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SymbolColors.entries.forEach { option ->
            val isSelected = option == selected
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .then(
                        if (isSelected) Modifier.border(2.dp, option.composeColor, CircleShape)
                        else Modifier
                    )
                    .padding(if (isSelected) 3.dp else 0.dp)
                    .clip(CircleShape)
                    .background(option.composeColor),
                contentAlignment = Alignment.Center
            ) {
                if (isSelected) {
                    Icon(Icons.Default.Check, null, tint = Color.White, modifier = Modifier.size(16.dp))
                }
            }
        }
    }
}
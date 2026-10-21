package com.javiviz.championshiptictactoe.features.players.presentation.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.javiviz.championshiptictactoe.app.enums.SymbolColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SymbolColorDropdown(
    selectedColor: SymbolColors,
    onValueChange: (SymbolColors) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = selectedColor.label,
            onValueChange = {},
            readOnly = true,
            label = { Text("Color preferido") },
            leadingIcon = {
                ColorIndicator(hexColor = selectedColor.hexColor)
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            SymbolColors.entries.forEach { color ->
                DropdownMenuItem(
                    text = {
                        Row (verticalAlignment = Alignment.CenterVertically) {
                            ColorIndicator(hexColor = color.hexColor)
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(text = color.label)
                        }
                    },
                    onClick = {
                        onValueChange(color)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
private fun ColorIndicator(hexColor: String) {
    val parsedColor = remember(hexColor) {
        Color("#$hexColor".toColorInt())
    }
    Box(
        modifier = Modifier
            .size(20.dp)
            .clip(CircleShape)
            .background(parsedColor)
    )
}
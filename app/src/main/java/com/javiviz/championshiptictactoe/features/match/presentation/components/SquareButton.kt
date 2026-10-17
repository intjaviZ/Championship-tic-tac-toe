package com.javiviz.championshiptictactoe.features.match.presentation.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SquareButton(simbolo : String, onClick : () -> Unit) {
    Button (onClick = onClick) {
        Text(text = simbolo)
    }
}
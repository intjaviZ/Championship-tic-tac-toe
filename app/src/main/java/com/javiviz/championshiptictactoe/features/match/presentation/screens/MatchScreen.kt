package com.javiviz.championshiptictactoe.features.match.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.javiviz.championshiptictactoe.features.match.presentation.components.GatoBoard

@Composable
fun MatchScreen() {
    Column() {
        GatoBoard()
        Button(onClick = { /*TODO*/ }) {
            Text("Reiniciar")
        }
    }
}
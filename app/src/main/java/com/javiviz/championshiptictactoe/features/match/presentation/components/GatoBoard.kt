package com.javiviz.championshiptictactoe.features.match.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable

@Composable
fun GatoBoard() {
    Column() {
        Row() {
            SquareButton("X", onClick = { /*TODO*/ })
            SquareButton("O", onClick = { /*TODO*/ })
            SquareButton("X", onClick = { /*TODO*/ })
        }
        Row() {
            SquareButton("X", onClick = { /*TODO*/ })
            SquareButton("O", onClick = { /*TODO*/ })
            SquareButton("X", onClick = { /*TODO*/ })
        }
        Row() {
            SquareButton("X", onClick = { /*TODO*/ })
            SquareButton("O", onClick = { /*TODO*/ })
            SquareButton("X", onClick = { /*TODO*/ })
        }
    }
}
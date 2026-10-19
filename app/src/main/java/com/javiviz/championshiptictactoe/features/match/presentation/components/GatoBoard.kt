package com.javiviz.championshiptictactoe.features.match.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import com.javiviz.championshiptictactoe.app.enums.Simbolos

@Composable
fun GatoBoard(simbolo : Array<Array<Simbolos>>, onClick : (x : Int, y : Int) -> Unit) {
    println(" CP: ${ simbolo.contentDeepToString() }")
    Column() {
        Row() {
            SquareButton(simbolo[0][0].label, onClick = { onClick(0, 0) })
            SquareButton(simbolo[0][1].label, onClick = { onClick(0, 1) })
            SquareButton(simbolo[0][2].label, onClick = { onClick(0, 2) })
        }
        Row() {
            SquareButton(simbolo[1][0].label, onClick = { onClick(1, 0) })
            SquareButton(simbolo[1][1].label, onClick = { onClick(1, 1) })
            SquareButton(simbolo[1][2].label, onClick = { onClick(1, 2) })
        }
        Row() {
            SquareButton(simbolo[2][0].label, onClick = { onClick(2, 0) })
            SquareButton(simbolo[2][1].label, onClick = { onClick(2, 1) })
            SquareButton(simbolo[2][2].label, onClick = { onClick(2, 2) })
        }
    }
}
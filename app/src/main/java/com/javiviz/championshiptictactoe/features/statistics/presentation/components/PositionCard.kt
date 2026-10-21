package com.javiviz.championshiptictactoe.features.statistics.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun PositionCard(apodo : String, pj: Int, pg : Int, pe : Int, pp : Int) {
    Column() {
        Text(apodo)
        Row() {
            Card() {
                Text("PJ: ${pj}")
            }
            Card() {
                Text("PG: ${pg}")
            }
            Card() {
                Text("PE: ${pe}")
            }
            Card() {
                Text("PP: ${pp}")
            }
        }
    }

}
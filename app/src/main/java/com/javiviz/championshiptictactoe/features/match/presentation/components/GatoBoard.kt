package com.javiviz.championshiptictactoe.features.match.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.javiviz.championshiptictactoe.app.enums.Simbolos

@Composable
fun GatoBoard(tablero: Array<Array<Simbolos>>,
              colorO: Color,
              colorX: Color,
              habilitado: Boolean,
              lineaGanadora: List<Pair<Int, Int>>,
              onClick: (x: Int, y: Int) -> Unit
) {
    Surface (
        shape = RoundedCornerShape(24.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            tablero.forEachIndexed { x, fila ->
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    fila.forEachIndexed { y, simbolo ->
                        val enLinea = (x to y) in lineaGanadora
                        SquareButton(
                            simbolo = simbolo,
                            color = if (simbolo == Simbolos.O) colorO else colorX,
                            enabled = habilitado && simbolo == Simbolos.VACIA,
                            ganadora = enLinea,
                            atenuada = lineaGanadora.isNotEmpty() && !enLinea,
                            onClick = { onClick(x, y) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}
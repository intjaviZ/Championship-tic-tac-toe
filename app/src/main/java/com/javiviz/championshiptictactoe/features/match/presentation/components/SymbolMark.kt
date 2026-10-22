package com.javiviz.championshiptictactoe.features.match.presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import com.javiviz.championshiptictactoe.app.enums.Simbolos

@Composable
fun SymbolMark(
    simbolo: Simbolos,
    color: Color,
    modifier: Modifier = Modifier,
    animated: Boolean = false
) {
    val progress = remember { Animatable(if (animated) 0f else 1f) }
    LaunchedEffect (Unit) { if (animated) progress.animateTo(1f, tween(250)) }

    Canvas (modifier) {
        val lado = size.minDimension
        val grosor = lado * 0.14f
        val inset = lado * 0.18f
        val p = progress.value

        if (simbolo == Simbolos.O) {
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360f * p,
                useCenter = false,
                topLeft = Offset(inset, inset),
                size = Size(lado - inset * 2, lado - inset * 2),
                style = Stroke(width = grosor, cap = StrokeCap.Round)
            )
        } else {
            val a = inset
            val b = lado - inset
            val p1 = (p * 2).coerceAtMost(1f)
            val p2 = ((p - 0.5f) * 2).coerceIn(0f, 1f)
            drawLine(color, Offset(a, a), Offset(a + (b - a) * p1, a + (b - a) * p1), grosor, StrokeCap.Round)
            if (p2 > 0f) {
                drawLine(color, Offset(b, a), Offset(b - (b - a) * p2, a + (b - a) * p2), grosor, StrokeCap.Round)
            }
        }
    }
}
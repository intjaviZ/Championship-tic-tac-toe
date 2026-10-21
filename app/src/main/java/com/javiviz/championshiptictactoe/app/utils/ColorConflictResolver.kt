package com.javiviz.championshiptictactoe.app.utils

import com.javiviz.championshiptictactoe.app.enums.SymbolColors
import kotlin.random.Random

class ColorConflictResolver(private val random: Random = Random.Default) {
    fun resolve(color1: SymbolColors, color2: SymbolColors) : SymbolColors {
        return if (color1 != color2) color2
        else SymbolColors.entries.filter { it != color1 }.random(random)
    }

}
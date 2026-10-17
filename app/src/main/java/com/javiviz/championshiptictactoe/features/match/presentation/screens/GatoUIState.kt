package com.javiviz.championshiptictactoe.features.match.presentation.screens

import com.javiviz.championshiptictactoe.app.enums.EstadoJuego
import com.javiviz.championshiptictactoe.app.enums.Simbolos

data class GatoUIState(
    val tablero : Array<Array<Simbolos>> = Array(3) { Array(3){ Simbolos.VACIA } },
    val estadoPartida : EstadoJuego = EstadoJuego.INICIAR,
    val turnoX : Boolean = true
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GatoUIState

        if (!tablero.contentDeepEquals(other.tablero)) return false

        return true
    }

    override fun hashCode(): Int {
        return tablero.contentDeepHashCode()
    }
}

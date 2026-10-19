package com.javiviz.championshiptictactoe.features.match.presentation.screens

import com.javiviz.championshiptictactoe.app.enums.EstadoJuego
import com.javiviz.championshiptictactoe.app.enums.Simbolos

data class MatchUIState(
    val tablero : Array<Array<Simbolos>> = Array(3) { Array(3){ Simbolos.VACIA } },
    val estadoPartida : EstadoJuego = EstadoJuego.INICIAR,
    val turnoX : Boolean = true,
    val ganador : Simbolos? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MatchUIState

        if (!tablero.contentDeepEquals(other.tablero)) return false
        if (estadoPartida != other.estadoPartida) return false
        if (turnoX != other.turnoX) return false

        return true
    }

    override fun hashCode(): Int {
        var result = tablero.contentDeepHashCode()
        result = 31 * result + estadoPartida.hashCode()
        result = 31 * result + turnoX.hashCode()
        return result
    }
}
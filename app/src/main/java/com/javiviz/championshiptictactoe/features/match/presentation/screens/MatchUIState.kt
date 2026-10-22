package com.javiviz.championshiptictactoe.features.match.presentation.screens

import com.javiviz.championshiptictactoe.app.enums.EstadoJuego
import com.javiviz.championshiptictactoe.app.enums.Simbolos
import com.javiviz.championshiptictactoe.app.enums.SymbolColors
import com.javiviz.championshiptictactoe.features.players.data.model.Player

data class MatchUIState(
    val tablero: Array<Array<Simbolos>> = Array(3) { Array(3) { Simbolos.VACIA } },
    val estadoPartida: EstadoJuego = EstadoJuego.INICIAR,
    val turnoJugador1: Boolean = true,
    val ganador: Simbolos? = null,
    val lineaGanadora: List<Pair<Int, Int>> = emptyList(),
    val ronda: Int = 1,
    val jugador1: Player? = null,
    val jugador2: Player? = null,
    val colorJ1: SymbolColors = SymbolColors.PURPLE,
    val colorJ2: SymbolColors = SymbolColors.CYAN,
    val colorJ2AlAzar: Boolean = false
) {
    val jugadas: Int get() = tablero.sumOf { fila -> fila.count { it != Simbolos.VACIA } }
    val casillasLibres: Int get() = 9 - jugadas
    val jugadorActual: Player? get() = if (turnoJugador1) jugador1 else jugador2
    val jugadorGanador: Player? get() = when (ganador) {
        Simbolos.O -> jugador1
        Simbolos.X -> jugador2
        else -> null
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MatchUIState

        if (!tablero.contentDeepEquals(other.tablero)) return false
        if (estadoPartida != other.estadoPartida) return false
        if (turnoJugador1 != other.turnoJugador1) return false
        if (ganador != other.ganador) return false
        if (lineaGanadora != other.lineaGanadora) return false
        if (ronda != other.ronda) return false
        if (jugador1 != other.jugador1) return false
        if (jugador2 != other.jugador2) return false
        if (colorJ1 != other.colorJ1) return false
        if (colorJ2 != other.colorJ2) return false
        if (colorJ2AlAzar != other.colorJ2AlAzar) return false

        return true
    }

    override fun hashCode(): Int {
        var result = tablero.contentDeepHashCode()
        result = 31 * result + estadoPartida.hashCode()
        result = 31 * result + turnoJugador1.hashCode()
        result = 31 * result + (ganador?.hashCode() ?: 0)
        result = 31 * result + lineaGanadora.hashCode()
        result = 31 * result + ronda
        result = 31 * result + (jugador1?.hashCode() ?: 0)
        result = 31 * result + (jugador2?.hashCode() ?: 0)
        result = 31 * result + colorJ1.hashCode()
        result = 31 * result + colorJ2.hashCode()
        result = 31 * result + colorJ2AlAzar.hashCode()
        return result
    }
}
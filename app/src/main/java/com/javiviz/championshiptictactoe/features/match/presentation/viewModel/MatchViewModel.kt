package com.javiviz.championshiptictactoe.features.match.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javiviz.championshiptictactoe.app.enums.EstadoJuego
import com.javiviz.championshiptictactoe.app.enums.ResultadoPartida
import com.javiviz.championshiptictactoe.app.enums.Simbolos
import com.javiviz.championshiptictactoe.app.enums.SymbolColors
import com.javiviz.championshiptictactoe.app.utils.lineasGanadoras
import com.javiviz.championshiptictactoe.features.match.data.dataSource.ActiveMatchStore
import com.javiviz.championshiptictactoe.features.match.presentation.screens.MatchUIState
import com.javiviz.championshiptictactoe.features.players.data.model.Player
import com.javiviz.championshiptictactoe.features.players.data.repository.PlayerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.Array

class MatchViewModel(
    private val repository: PlayerRepository,
) : ViewModel() {
    private val _matchState = MutableStateFlow(MatchUIState())
    val matchState: StateFlow<MatchUIState> = _matchState.asStateFlow()

    fun takeTurn(x: Int, y: Int) {
        val s = _matchState.value
        if (s.estadoPartida != EstadoJuego.JUGANDO) return
        if (s.tablero[x][y] != Simbolos.VACIA) return

        val simbolo = if (s.turnoJugador1) Simbolos.O else Simbolos.X
        val tablero = s.tablero.map { it.clone() }.toTypedArray()
        tablero[x][y] = simbolo

        val linea = findWinningLine(tablero)
        val resultado = when {
            linea != null -> simbolo
            tablero.all { fila -> fila.none { it == Simbolos.VACIA } } -> Simbolos.VACIA
            else -> null
        }

        _matchState.update {
            it.copy(
                tablero = tablero,
                turnoJugador1 = !it.turnoJugador1,
                estadoPartida = if (resultado != null) EstadoJuego.FINALIZADO else EstadoJuego.JUGANDO,
                ganador = resultado,
                lineaGanadora = linea.orEmpty()
            )
        }
        resultado?.let { saveResult(it, s.jugador1, s.jugador2) }
    }

    fun resetGame() {
        _matchState.update {
            it.copy(
                tablero = Array(3) { Array(3) { Simbolos.VACIA } },
                turnoJugador1 = true,
                estadoPartida = EstadoJuego.JUGANDO,
                ganador = null,
                lineaGanadora = emptyList(),
                ronda = if (it.estadoPartida == EstadoJuego.FINALIZADO) it.ronda + 1 else it.ronda
            )
        }
    }
    private fun saveResult(resultado: Simbolos, j1: Player?, j2: Player?) {
        if (j1 == null || j2 == null) return
        val (r1, r2) = when (resultado) {
            Simbolos.O -> ResultadoPartida.WIN to ResultadoPartida.LOSS
            Simbolos.X -> ResultadoPartida.LOSS to ResultadoPartida.WIN
            Simbolos.VACIA -> ResultadoPartida.DRAW to ResultadoPartida.DRAW
        }
        viewModelScope.launch {
            repository.updateStatistics(j1.id, r1)
            repository.updateStatistics(j2.id, r2)
        }
    }

    private fun findWinningLine(tablero: Array<Array<Simbolos>>): List<Pair<Int, Int>>? =
        lineasGanadoras.firstOrNull { linea ->
            val (x0, y0) = linea[0]
            val s = tablero[x0][y0]
            s != Simbolos.VACIA && linea.all { (x, y) -> tablero[x][y] == s }
        }

    fun preparePlayers(player1: Player, player2: Player) {
        val repetido = player1.colorPreferido == player2.colorPreferido
        val colorJ2 = if (repetido) {
            SymbolColors.entries.filter { it != player1.colorPreferido }.random()
        } else player2.colorPreferido

        _matchState.value = MatchUIState(
            estadoPartida = EstadoJuego.JUGANDO,
            jugador1 = player1,
            jugador2 = player2,
            colorJ1 = player1.colorPreferido,
            colorJ2 = colorJ2,
            colorJ2AlAzar = repetido
        )
    }
    fun startGame() {
        _matchState.update { it.copy(estadoPartida = EstadoJuego.JUGANDO) }

    }
}

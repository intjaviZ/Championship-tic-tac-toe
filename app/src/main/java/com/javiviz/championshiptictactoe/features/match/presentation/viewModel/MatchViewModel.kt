package com.javiviz.championshiptictactoe.features.match.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javiviz.championshiptictactoe.app.enums.EstadoJuego
import com.javiviz.championshiptictactoe.app.enums.ResultadoPartida
import com.javiviz.championshiptictactoe.app.enums.Simbolos
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
    private val activeMatchStore: ActiveMatchStore
) : ViewModel() {
    private val _matchState = MutableStateFlow(MatchUIState())
    val matchState: StateFlow<MatchUIState> = _matchState.asStateFlow()

    fun takeTurn(x : Int, y : Int) {
        val currentState = _matchState.value

        if(currentState.estadoPartida != EstadoJuego.JUGANDO) return
        if (currentState.tablero[x][y] != Simbolos.VACIA) return

        val nuevoSimbolo = setTurn()

        _matchState.update { state ->
            val nuevoTablero = state.tablero.map { it.clone() }.toTypedArray()
            nuevoTablero[x][y] = nuevoSimbolo

            state.copy(tablero = nuevoTablero)
        }
        checkGameState()
    }

    fun setTurn() : Simbolos {
        _matchState.update { it.copy(turnoX = !it.turnoX) }
        return if (!matchState.value.turnoX) Simbolos.X else Simbolos.O
    }

    fun resetGame() {
        _matchState.update {
            it.copy(
                tablero = Array(3) { Array(3) { Simbolos.VACIA } },
                turnoX = true,
                estadoPartida = EstadoJuego.JUGANDO,
                ganador = null
            )
        }
    }

    fun preparePlayers(player1 : Player, player2 : Player) {
        _matchState.update { it.copy(jugador1 = player1, jugador2 = player2) }
    }
    fun startGame() {
        _matchState.update { it.copy(estadoPartida = EstadoJuego.JUGANDO) }

    }

    fun checkGameState(): Simbolos? {
        val tablero = _matchState.value.tablero

        val ganador = findWinner(tablero)
        val tableroLleno = tablero.all { fila -> fila.none { it == Simbolos.VACIA } }

        val resultado = when {
            ganador != null -> ganador
            tableroLleno -> Simbolos.VACIA
            else -> null
        }

        if (resultado != null) {
            when(resultado) {
                Simbolos.X -> {
                    viewModelScope.launch {
                        repository.updateStatistics(matchState.value.jugador1!!.id, ResultadoPartida.WIN)
                        repository.updateStatistics(matchState.value.jugador2!!.id, ResultadoPartida.LOSS)
                    }
                }
                Simbolos.O -> {
                    viewModelScope.launch {
                        repository.updateStatistics(matchState.value.jugador1!!.id, ResultadoPartida.LOSS)
                        repository.updateStatistics(matchState.value.jugador2!!.id, ResultadoPartida.WIN)
                    }
                }
                Simbolos.VACIA -> {
                    viewModelScope.launch {
                        repository.updateStatistics(matchState.value.jugador1!!.id, ResultadoPartida.DRAW)
                        repository.updateStatistics(matchState.value.jugador2!!.id, ResultadoPartida.DRAW)
                    }
                }

            }
            _matchState.update {
                it.copy(estadoPartida = EstadoJuego.FINALIZADO, ganador = resultado)
            }
        }
        return resultado
    }

    private fun findWinner(tablero: Array<Array<Simbolos>>): Simbolos? {
        for (linea in lineasGanadoras) {
            val (x0, y0) = linea[0]
            val simbolo = tablero[x0][y0]
            if (simbolo == Simbolos.VACIA) continue

            if (linea.all { (x, y) -> tablero[x][y] == simbolo }) return simbolo
        }
        return null
    }

    fun turnMatch() : String? {
        val matchState = _matchState.value
        return if (matchState.turnoX) matchState.jugador1?.apodo else matchState.jugador2?.apodo
    }
}

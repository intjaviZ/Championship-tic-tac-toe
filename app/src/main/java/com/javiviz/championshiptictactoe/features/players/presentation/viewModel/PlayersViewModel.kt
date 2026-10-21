package com.javiviz.championshiptictactoe.features.players.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javiviz.championshiptictactoe.app.enums.SymbolColors
import com.javiviz.championshiptictactoe.features.players.data.model.Player
import com.javiviz.championshiptictactoe.features.players.data.model.PlayerStatistics
import com.javiviz.championshiptictactoe.features.players.data.repository.PlayerRepository
import com.javiviz.championshiptictactoe.features.players.presentation.screens.CreatePlayerUIState
import com.javiviz.championshiptictactoe.features.players.presentation.screens.PlayersUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlayersViewModel (
    private val repository: PlayerRepository
) : ViewModel() {
    private val _playersState = MutableStateFlow(PlayersUIState())
    private val _createPlayerState = MutableStateFlow(CreatePlayerUIState())
    val playersState : StateFlow<PlayersUIState> = _playersState.asStateFlow()
    val createPlayerUIState : StateFlow<CreatePlayerUIState> = _createPlayerState.asStateFlow()

    init {
        getAllPlayers()
    }

    fun getAllPlayers() {
        repository.observePlayers()
            .onEach { list -> _playersState.update { it.copy(players = list) } }
            .launchIn(viewModelScope)
    }

    suspend fun getPlayerById(playerId: String) {
        _playersState.update { it.copy(player = repository.getPlayerById(playerId)) }
    }

    fun createPlayer() {
        val state = _createPlayerState.value
        if (state.apodo.isBlank()) return
        viewModelScope.launch {
            repository.createPlayer(state.apodo, state.color)
        }
    }

    fun updatePlayer(player: Player) {
        if (player.apodo.isBlank()) return
        viewModelScope.launch {
            repository.updatePlayer(player)
        }

    }

    fun deletePlayer(playerId: String) {
        viewModelScope.launch {
            repository.deletePlayer(playerId)
        }
    }

    fun setApodo(apodo : String) {
        _createPlayerState.update { it.copy(apodo = apodo) }
    }
    fun setColor(color : SymbolColors) {
        _createPlayerState.update { it.copy(color = color) }
    }
}
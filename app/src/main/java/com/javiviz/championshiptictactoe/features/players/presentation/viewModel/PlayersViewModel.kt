package com.javiviz.championshiptictactoe.features.players.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.javiviz.championshiptictactoe.app.enums.SymbolColors
import com.javiviz.championshiptictactoe.features.players.data.model.Player
import com.javiviz.championshiptictactoe.features.players.data.model.PlayerStatistics
import com.javiviz.championshiptictactoe.features.players.data.repository.PlayerRepository
import com.javiviz.championshiptictactoe.features.players.presentation.screens.CreatePlayerUIState
import com.javiviz.championshiptictactoe.features.players.presentation.screens.PlayersUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

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
        val players = repository.getAllPlayers()
        _playersState.update { it.copy(players = players) }
    }

    fun getPlayerById(playerId: String) {
        _playersState.update { it.copy(player = repository.getPlayerById(playerId)) }
    }

    fun createPlayer() {
        if (_createPlayerState.value.apodo.isBlank()) return
        val newPlayer : Player = Player(
            id = repository.getIdPlayer(),
            apodo = _createPlayerState.value.apodo,
            colorPreferido = _createPlayerState.value.color,
            statistics = PlayerStatistics(
                totalMatches = 0,
                wins = 0,
                losses = 0,
                draws = 0
            )
        )
        repository.createPlayer(newPlayer)
        println("Repository: ${repository.getAllPlayers()}")
        println("ViewModel: ${playersState.value.players}")
    }
    fun updatePlayer(player: Player) {
        if (player.apodo.isBlank()) return
        repository.updatePlayer(player)
    }
    fun deletePlayer(playerId: String) {
        repository.deletePlayer(playerId)
    }

    fun setApodo(apodo : String) {
        _createPlayerState.update { it.copy(apodo = apodo) }
    }
    fun setColor(color : SymbolColors) {
        _createPlayerState.update { it.copy(color = color) }
    }
}
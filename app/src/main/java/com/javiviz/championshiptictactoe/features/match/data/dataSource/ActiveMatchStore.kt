package com.javiviz.championshiptictactoe.features.match.data.dataSource

import com.javiviz.championshiptictactoe.app.enums.Simbolos
import com.javiviz.championshiptictactoe.app.enums.SymbolColors
import com.javiviz.championshiptictactoe.features.players.data.model.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class MatchPlayer(val player: Player, val simbolo: Simbolos, val color: SymbolColors)
data class ActiveMatch(val jugador1: MatchPlayer, val jugador2: MatchPlayer)

class ActiveMatchStore {
    private val _activeMatch = MutableStateFlow<ActiveMatch?>(null)
    val activeMatch: StateFlow<ActiveMatch?> = _activeMatch.asStateFlow()

    fun start(match: ActiveMatch) { _activeMatch.value = match }
    fun clear() { _activeMatch.value = null }
}
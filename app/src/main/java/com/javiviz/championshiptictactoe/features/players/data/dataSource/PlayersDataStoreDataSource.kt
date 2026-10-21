package com.javiviz.championshiptictactoe.features.players.data.dataSource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.javiviz.championshiptictactoe.app.enums.ResultadoPartida
import com.javiviz.championshiptictactoe.app.enums.SymbolColors
import com.javiviz.championshiptictactoe.app.utils.SEED_PLAYERS
import com.javiviz.championshiptictactoe.features.players.data.model.Player
import com.javiviz.championshiptictactoe.features.players.data.model.PlayerStatistics
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.UUID

internal val Context.playersDataStore by preferencesDataStore(name = "players")

class PlayersDataStoreDataSource(
    private val dataStore: DataStore<androidx.datastore.preferences.core.Preferences>,
    private val json: Json = Json { ignoreUnknownKeys = true }
) {
    private val playersKey = stringPreferencesKey("players_json")

    val players: Flow<List<Player>> = dataStore.data.map { prefs ->
        prefs[playersKey]?.let { json.decodeFromString<List<Player>>(it) } ?: SEED_PLAYERS
    }

    suspend fun getPlayerById(id: String): Player? =
        players.first().find { it.id == id }

    suspend fun createPlayer(apodo: String, color: SymbolColors): Player {
        val player = Player(
            id = UUID.randomUUID().toString(),
            apodo = apodo,
            colorPreferido = color,
            statistics = PlayerStatistics()
        )
        modify { it + player }
        return player
    }

    suspend fun updatePlayer(player: Player) =
        modify { list -> list.map { if (it.id == player.id) player else it } }

    suspend fun deletePlayer(id: String) =
        modify { list -> list.filterNot { it.id == id } }

    suspend fun updateStatistics(id: String, result: ResultadoPartida) =
        modify { list ->
            list.map { p ->
                if (p.id != id) p else {
                    val s = p.statistics
                    p.copy(statistics = s.copy(
                        totalMatches = s.totalMatches + 1,
                        wins = s.wins + if (result == ResultadoPartida.WIN) 1 else 0,
                        draws = s.draws + if (result == ResultadoPartida.DRAW) 1 else 0,
                        losses = s.losses + if (result == ResultadoPartida.LOSS) 1 else 0
                    ))
                }
            }
        }

    private suspend fun modify(transform: (List<Player>) -> List<Player>) {
        dataStore.edit { prefs ->
            val current = prefs[playersKey]
                ?.let { json.decodeFromString<List<Player>>(it) } ?: SEED_PLAYERS
            prefs[playersKey] = json.encodeToString(transform(current))
        }
    }
}
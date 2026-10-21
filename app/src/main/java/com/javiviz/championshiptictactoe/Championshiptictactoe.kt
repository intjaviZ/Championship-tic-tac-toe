package com.javiviz.championshiptictactoe

import android.app.Application
import com.javiviz.championshiptictactoe.features.match.di.MatchModule
import com.javiviz.championshiptictactoe.features.players.di.PlayersModule

class Championshiptictactoe : Application() {
    val playersModule by lazy { PlayersModule(applicationContext) }
    val matchModule by lazy {
        MatchModule(playersModule.repository)
    }
}
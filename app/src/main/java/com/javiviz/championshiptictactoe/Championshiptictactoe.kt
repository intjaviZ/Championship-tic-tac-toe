package com.javiviz.championshiptictactoe

import android.app.Application
import com.javiviz.championshiptictactoe.features.players.di.PlayersModule

class Championshiptictactoe : Application() {
    val playersModule : PlayersModule by lazy {
        PlayersModule()
    }
}
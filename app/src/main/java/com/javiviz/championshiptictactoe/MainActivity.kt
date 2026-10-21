package com.javiviz.championshiptictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.javiviz.championshiptictactoe.features.main.presentation.MainScreen
import com.javiviz.championshiptictactoe.features.match.presentation.screens.MatchScreen
import com.javiviz.championshiptictactoe.features.players.presentation.screens.CreatePlayerScreen
import com.javiviz.championshiptictactoe.features.players.presentation.screens.PlayersScreen
import com.javiviz.championshiptictactoe.features.statistics.presentation.screens.StatisticsScreen
import com.javiviz.championshiptictactoe.ui.theme.ChampionshipTicTacToeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChampionshipTicTacToeTheme {
                MainScreen()
            }
        }
    }
}
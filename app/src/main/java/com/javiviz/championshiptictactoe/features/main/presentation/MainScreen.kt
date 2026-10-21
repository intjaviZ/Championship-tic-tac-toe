package com.javiviz.championshiptictactoe.features.main.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.javiviz.championshiptictactoe.Championshiptictactoe
import com.javiviz.championshiptictactoe.app.enums.Sections
import com.javiviz.championshiptictactoe.features.match.presentation.screens.MatchScreen
import com.javiviz.championshiptictactoe.features.match.presentation.viewModel.MatchViewModel
import com.javiviz.championshiptictactoe.features.matchSetup.presentation.screens.MatchSetupScreen
import com.javiviz.championshiptictactoe.features.players.presentation.screens.CreatePlayerScreen
import com.javiviz.championshiptictactoe.features.statistics.presentation.screens.StatisticsScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel : MainViewModel = viewModel(),
    matchViewModel: MatchViewModel = viewModel(
        factory = (LocalContext.current.applicationContext as Championshiptictactoe).matchModule.matchFactory
    )
) {
    val currentSection by viewModel.currentSection.collectAsStateWithLifecycle()
    val isDark by viewModel.isDarkTheme.collectAsStateWithLifecycle()

    val scrollState = rememberScrollState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet() {
                Text(
                    text = "Championshipt TicTacToe",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
                HorizontalDivider()
                Spacer(modifier = Modifier.height(8.dp))

                Sections.entries.forEach { section ->
                    NavigationDrawerItem(
                        label = { Text(section.label) },
                        selected = currentSection == section,
                        onClick = {
                            viewModel.onSectionSelected(section)
                            coroutineScope.launch { drawerState.close() }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Modo oscuro", style = MaterialTheme.typography.bodyLarge)

                    Switch(
                        checked = isDark,
                        onCheckedChange = { viewModel.setDarkTheme(it) }
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(currentSection.label) },
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch { drawerState.open() }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Abrir menú"
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .imePadding()
                    .verticalScroll(scrollState),

                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                when(currentSection) {
                    Sections.HOME -> MatchSetupScreen(onStartMatch = { p1, p2 ->
                        matchViewModel.preparePlayers(p1, p2)
                        viewModel.onSectionSelected(Sections.MATCH)
                    })
                    Sections.NEW_PLAYER -> CreatePlayerScreen()
                    Sections.STATISTICS -> StatisticsScreen()
                    else -> {Text("No hay contenido para esta sección")}
                }
            }
        }
    }

}
package com.javiviz.championshiptictactoe.features.main.presentation

import androidx.lifecycle.ViewModel
import com.javiviz.championshiptictactoe.app.enums.Sections
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private var _currentSection = MutableStateFlow(Sections.HOME)
    private val _isDarkTheme = MutableStateFlow(true)
    val currentSection : StateFlow<Sections> = _currentSection.asStateFlow()
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme.asStateFlow()

    fun setDarkTheme(enabled: Boolean) {
        _isDarkTheme.value = enabled
    }
    fun onSectionSelected(section: Sections) {
        _currentSection.value = section
    }
}
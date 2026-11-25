package com.example.recipeapp.ui.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.SettingsRepository
import com.example.recipeapp.data.SortOrder
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = SettingsRepository(application)

    val isDarkTheme: StateFlow<Boolean> = repository.isDarkTheme.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = false
    )

    val sortOrder: StateFlow<SortOrder> = repository.sortOrder.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SortOrder.BY_DATE
    )

    fun toggleTheme() {
        viewModelScope.launch {
            repository.setDarkTheme(!isDarkTheme.value)
        }
    }

    fun setSortOrder(order: SortOrder) {
        viewModelScope.launch {
            repository.setSortOrder(order)
        }
    }
}

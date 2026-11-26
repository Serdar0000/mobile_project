package com.example.recipeapp.ui.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.FavoriteRepository
import com.example.recipeapp.data.SettingsRepository
import com.example.recipeapp.data.SortOrder
import com.example.recipeapp.data.local.AppDatabase
import com.example.recipeapp.data.local.FavoriteEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = FavoriteRepository(
        AppDatabase.getInstance(application).favoriteDao()
    )

    private val settingsRepository = SettingsRepository(application)

    val favorites: StateFlow<List<FavoriteEntity>> = combine(
        repository.getFavorites(),
        settingsRepository.sortOrder
    ) { list, order ->
        when (order) {
            SortOrder.BY_NAME -> list.sortedBy { it.title.lowercase() }
            SortOrder.BY_DATE -> list.sortedByDescending { it.addedAt }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    fun updateNote(id: String, note: String, rating: Int) {
        viewModelScope.launch {
            repository.updateNote(id, note, rating)
        }
    }

    fun delete(id: String) {
        viewModelScope.launch {
            repository.remove(id)
        }
    }
}

package com.example.recipeapp.ui.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.FavoriteRepository
import com.example.recipeapp.data.local.AppDatabase
import com.example.recipeapp.data.local.FavoriteEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = FavoriteRepository(
        AppDatabase.getInstance(application).favoriteDao()
    )

    val favorites: StateFlow<List<FavoriteEntity>> = repository.getFavorites().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    fun delete(id: String) {
        viewModelScope.launch {
            repository.remove(id)
        }
    }
}

package com.example.recipeapp.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.R
import com.example.recipeapp.data.FavoriteRepository
import com.example.recipeapp.data.Recipe
import com.example.recipeapp.data.local.AppDatabase
import com.example.recipeapp.data.remote.RecipeRemoteRepository
import com.example.recipeapp.ui.UiState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException

class DetailViewModel(
    application: Application,
    savedStateHandle: SavedStateHandle
) : AndroidViewModel(application) {

    private val remoteRepository = RecipeRemoteRepository()
    private val favoriteRepository = FavoriteRepository(
        AppDatabase.getInstance(application).favoriteDao()
    )

    private val _uiState = MutableStateFlow<UiState<Recipe>>(UiState.Loading)
    val uiState: StateFlow<UiState<Recipe>> = _uiState.asStateFlow()

    private val recipeId: String = savedStateHandle["recipeId"] ?: ""

    val isFavorite: StateFlow<Boolean> = favoriteRepository.isFavorite(recipeId).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = false
    )

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = try {
                val recipe = remoteRepository.getRecipe(recipeId)
                if (recipe == null) UiState.Empty else UiState.Success(recipe)
            } catch (e: CancellationException) {
                throw e
            } catch (e: IOException) {
                UiState.Error(getString(R.string.detail_error_no_internet))
            } catch (e: Exception) {
                UiState.Error(getString(R.string.detail_error_unknown))
            }
        }
    }

    fun retry() {
        load()
    }

    fun toggleFavorite() {
        val recipe = (uiState.value as? UiState.Success)?.data ?: return
        viewModelScope.launch {
            if (isFavorite.value) {
                favoriteRepository.remove(recipe.id)
            } else {
                favoriteRepository.add(recipe)
            }
        }
    }

    private fun getString(resId: Int): String = getApplication<Application>().getString(resId)
}

package com.example.recipeapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.Recipe
import com.example.recipeapp.data.RecipeRepository
import com.example.recipeapp.data.remote.RecipeRemoteRepository
import com.example.recipeapp.ui.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    private val remoteRepository = RecipeRemoteRepository()

    private val _uiState = MutableStateFlow<UiState<Recipe>>(UiState.Loading)
    val uiState: StateFlow<UiState<Recipe>> = _uiState.asStateFlow()

    fun load(id: String) {
        viewModelScope.launch {
            val recipe = remoteRepository.getRecipe(id)
            _uiState.value = if (recipe == null) UiState.Empty else UiState.Success(recipe)
        }
    }

    fun toggleFavorite(id: String) {
        RecipeRepository.toggleFavorite(id)
    }
}

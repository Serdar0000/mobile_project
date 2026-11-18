package com.example.recipeapp.ui.home

import androidx.lifecycle.ViewModel
import com.example.recipeapp.data.Recipe
import com.example.recipeapp.data.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class HomeUiState(val searchText: String = "")

class HomeViewModel : ViewModel() {
    val recipes: StateFlow<List<Recipe>> = RecipeRepository.recipes

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun onSearchChange(text: String) {
        _uiState.update { it.copy(searchText = text) }
    }

    fun filter(recipes: List<Recipe>, searchText: String): List<Recipe> =
        recipes.filter { it.title.contains(searchText, ignoreCase = true) }
}

package com.example.recipeapp.ui.detail

import androidx.lifecycle.ViewModel
import com.example.recipeapp.data.Recipe
import com.example.recipeapp.data.RecipeRepository
import kotlinx.coroutines.flow.StateFlow

class DetailViewModel : ViewModel() {
    val recipes: StateFlow<List<Recipe>> = RecipeRepository.recipes

    fun toggleFavorite(id: Int) {
        RecipeRepository.toggleFavorite(id)
    }
}

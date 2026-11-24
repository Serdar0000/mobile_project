package com.example.recipeapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.Recipe
import com.example.recipeapp.data.RecipeRepository
import com.example.recipeapp.ui.UiState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class HomeViewModel : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText.asStateFlow()

    private val _uiState = MutableStateFlow<UiState<List<Recipe>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Recipe>>> = _uiState.asStateFlow()

    private var loadJob: Job? = null

    init {
        loadRecipes(query = "")
    }

    fun onSearchChange(text: String) {
        _searchText.value = text
        loadRecipes(query = text, debounceMillis = SEARCH_DEBOUNCE_MILLIS)
    }

    fun retry() {
        loadRecipes(query = _searchText.value)
    }

    private fun loadRecipes(query: String, debounceMillis: Long = 0) {
        loadJob?.cancel()
        loadJob = viewModelScope.launch {
            delay(debounceMillis)
            _uiState.value = UiState.Loading
            _uiState.value = try {
                val recipes = searchRecipes(query)
                if (recipes.isEmpty()) UiState.Empty else UiState.Success(recipes)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                UiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    private suspend fun searchRecipes(query: String): List<Recipe> {
        delay(FAKE_NETWORK_DELAY_MILLIS)
        if (query.equals("error", ignoreCase = true)) {
            throw IOException("Test error: type anything else to load recipes")
        }
        return RecipeRepository.recipes.value.filter {
            it.title.contains(query, ignoreCase = true)
        }
    }

    private companion object {
        const val SEARCH_DEBOUNCE_MILLIS = 400L
        const val FAKE_NETWORK_DELAY_MILLIS = 1000L
    }
}

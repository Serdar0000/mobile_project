package com.example.recipeapp.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.R
import com.example.recipeapp.data.Recipe
import com.example.recipeapp.data.RecipeRepository
import com.example.recipeapp.data.remote.RecipeRemoteRepository
import com.example.recipeapp.ui.UiState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    private val remoteRepository = RecipeRemoteRepository()

    private val _uiState = MutableStateFlow<UiState<Recipe>>(UiState.Loading)
    val uiState: StateFlow<UiState<Recipe>> = _uiState.asStateFlow()

    private var recipeId: String = ""

    fun load(id: String) {
        recipeId = id
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = try {
                val recipe = remoteRepository.getRecipe(id)
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
        load(recipeId)
    }

    fun toggleFavorite(id: String) {
        RecipeRepository.toggleFavorite(id)
    }

    private fun getString(resId: Int): String = getApplication<Application>().getString(resId)
}

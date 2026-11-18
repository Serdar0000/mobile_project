package com.example.recipeapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipeapp.ui.components.RecipeCard
import com.example.recipeapp.ui.components.RecipeTopAppBar

@Composable
fun HomeScreen(
    onRecipeClick: (Int) -> Unit,
    onFavoritesClick: () -> Unit,
    viewModel: HomeViewModel = viewModel()
) {
    val recipes by viewModel.recipes.collectAsState()

    Scaffold(
        topBar = {
            RecipeTopAppBar(title = "Recipes", canNavigateBack = false)
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(innerPadding)
        ) {
            items(recipes, key = { it.id }) { recipe ->
                RecipeCard(recipe = recipe, onClick = {})
            }
        }
    }
}

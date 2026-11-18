package com.example.recipeapp.ui.favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipeapp.R
import com.example.recipeapp.ui.components.RecipeTopAppBar

@Composable
fun FavoritesScreen(
    onRecipeClick: (Int) -> Unit,
    onBack: () -> Unit,
    viewModel: FavoritesViewModel = viewModel()
) {
    val recipes by viewModel.recipes.collectAsState()
    val favorites = recipes.filter { it.isFavorite }

    Scaffold(
        topBar = {
            RecipeTopAppBar(
                title = stringResource(R.string.favorites_title),
                canNavigateBack = true,
                onBack = onBack
            )
        }
    ) { innerPadding ->
        if (favorites.isEmpty()) {
            EmptyFavoritesMessage(modifier = Modifier.padding(innerPadding))
        }
    }
}

@Composable
private fun EmptyFavoritesMessage(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.no_favorites),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}

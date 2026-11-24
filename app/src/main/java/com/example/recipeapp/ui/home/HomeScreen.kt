package com.example.recipeapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipeapp.R
import com.example.recipeapp.ui.components.RecipeCard
import com.example.recipeapp.ui.components.RecipeTopAppBar

@Composable
fun HomeScreen(
    onRecipeClick: (String) -> Unit,
    onFavoritesClick: () -> Unit,
    viewModel: HomeViewModel = viewModel()
) {
    val recipes by viewModel.recipes.collectAsState()
    val uiState by viewModel.uiState.collectAsState()
    val filteredRecipes = viewModel.filter(recipes, uiState.searchText)

    Scaffold(
        topBar = {
            RecipeTopAppBar(
                title = stringResource(R.string.home_title),
                canNavigateBack = false,
                actions = {
                    IconButton(onClick = onFavoritesClick) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = stringResource(R.string.open_favorites)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            OutlinedTextField(
                value = uiState.searchText,
                onValueChange = { viewModel.onSearchChange(it) },
                label = { Text(stringResource(R.string.search_label)) },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            )
            if (filteredRecipes.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.no_recipes_found),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(filteredRecipes, key = { it.id }) { recipe ->
                        RecipeCard(
                            recipe = recipe,
                            onClick = { onRecipeClick(recipe.id) }
                        )
                    }
                }
            }
        }
    }
}

package com.example.recipeapp.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.recipeapp.R
import com.example.recipeapp.data.Recipe
import com.example.recipeapp.ui.UiState
import com.example.recipeapp.ui.components.RecipeTopAppBar
import com.example.recipeapp.ui.theme.RecipeAppTheme

@Composable
fun RecipeDetailScreen(
    recipeId: String,
    onBack: () -> Unit,
    viewModel: DetailViewModel = viewModel()
) {
    LaunchedEffect(recipeId) {
        viewModel.load(recipeId)
    }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val recipe = (uiState as? UiState.Success)?.data

    Scaffold(
        topBar = {
            RecipeTopAppBar(
                title = recipe?.title ?: stringResource(R.string.detail_default_title),
                canNavigateBack = true,
                onBack = onBack
            )
        }
    ) { innerPadding ->
        if (recipe == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(R.string.detail_recipe_not_found))
            }
        } else {
            RecipeDetailContent(
                recipe = recipe,
                onFavoriteClick = { viewModel.toggleFavorite(recipe.id) },
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
private fun RecipeDetailContent(
    recipe: Recipe,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        AsyncImage(
            model = recipe.imageUrl,
            contentDescription = recipe.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        )
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = recipe.title,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = stringResource(
                    R.string.detail_category_area,
                    recipe.category,
                    recipe.area
                ),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.detail_ingredients),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            recipe.ingredients.forEach { ingredient ->
                Text(
                    text = stringResource(R.string.detail_list_item, ingredient),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.detail_steps),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            recipe.instructions.lines().filter { it.isNotBlank() }.forEach { step ->
                Text(
                    text = stringResource(R.string.detail_list_item, step),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = onFavoriteClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = if (recipe.isFavorite) {
                        Icons.Filled.Favorite
                    } else {
                        Icons.Filled.FavoriteBorder
                    },
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))
                Text(
                    text = if (recipe.isFavorite) {
                        stringResource(R.string.detail_remove_from_favorites)
                    } else {
                        stringResource(R.string.detail_add_to_favorites)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeDetailContentPreview() {
    RecipeAppTheme {
        RecipeDetailContent(
            recipe = Recipe(
                id = "5",
                title = "Plov",
                category = "Dinner",
                area = "Uzbek",
                imageUrl = "",
                ingredients = listOf("500 g rice", "500 g beef or lamb", "3 carrots", "2 onions"),
                instructions = "Fry the meat until brown.\nAdd the onions and carrots.\nAdd the rice and cook for 30 minutes."
            ),
            onFavoriteClick = {}
        )
    }
}

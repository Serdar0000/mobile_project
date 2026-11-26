package com.example.recipeapp.ui.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipeapp.R
import com.example.recipeapp.data.local.FavoriteEntity
import com.example.recipeapp.ui.components.RecipeTopAppBar
import com.example.recipeapp.ui.settings.SortMenu

@Composable
fun FavoritesScreen(
    onRecipeClick: (String) -> Unit,
    onBack: () -> Unit,
    viewModel: FavoritesViewModel = viewModel()
) {
    val favorites by viewModel.favorites.collectAsStateWithLifecycle()
    var editing by remember { mutableStateOf<FavoriteEntity?>(null) }

    Scaffold(
        topBar = {
            RecipeTopAppBar(
                title = stringResource(R.string.favorites_title),
                canNavigateBack = true,
                onBack = onBack,
                actions = { SortMenu() }
            )
        }
    ) { innerPadding ->
        if (favorites.isEmpty()) {
            EmptyFavoritesMessage(modifier = Modifier.padding(innerPadding))
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(innerPadding)
            ) {
                items(favorites, key = { it.id }) { favorite ->
                    FavoriteCard(
                        favorite = favorite,
                        onClick = { onRecipeClick(favorite.id) },
                        onEditNote = { editing = favorite },
                        onDelete = { viewModel.delete(favorite.id) }
                    )
                }
            }
        }
    }

    editing?.let { favorite ->
        NoteDialog(
            initialNote = favorite.note,
            initialRating = favorite.rating,
            onDismiss = { editing = null },
            onSave = { note, rating ->
                viewModel.updateNote(favorite.id, note, rating)
                editing = null
            }
        )
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

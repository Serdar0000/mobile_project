package com.example.recipeapp.ui.favorites

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun FavoritesScreen(
    onRecipeClick: (Int) -> Unit,
    onBack: () -> Unit
) {
    Text(text = "Favorites")
}

package com.example.recipeapp.ui.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(
    onRecipeClick: (Int) -> Unit,
    onFavoritesClick: () -> Unit
) {
    Text(text = "Home")
}

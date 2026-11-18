package com.example.recipeapp.ui.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun RecipeDetailScreen(
    recipeId: Int,
    onBack: () -> Unit
) {
    Text(text = "Detail of recipe $recipeId")
}

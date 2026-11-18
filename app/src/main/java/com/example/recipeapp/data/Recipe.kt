package com.example.recipeapp.data

import androidx.annotation.DrawableRes

data class Recipe(
    val id: Int,
    val title: String,
    val category: String,
    val timeMinutes: Int,
    val ingredients: List<String>,
    val steps: List<String>,
    @DrawableRes val imageRes: Int,
    val isFavorite: Boolean = false
)

package com.example.recipeapp.data

data class Recipe(
    val id: String,
    val title: String,
    val category: String,
    val area: String,
    val imageUrl: String,
    val ingredients: List<String>,
    val instructions: String,
    val youtubeUrl: String = "",
    val isFavorite: Boolean = false
)

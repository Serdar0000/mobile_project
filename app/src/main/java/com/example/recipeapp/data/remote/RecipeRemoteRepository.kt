package com.example.recipeapp.data.remote

import com.example.recipeapp.data.Recipe

class RecipeRemoteRepository(
    private val api: MealApi = RetrofitClient.api
) {
    suspend fun searchRecipes(query: String): List<Recipe> =
        api.search(query).meals.orEmpty().map { it.toRecipe() }

    suspend fun getRecipe(id: String): Recipe? =
        api.lookup(id).meals?.firstOrNull()?.toRecipe()
}

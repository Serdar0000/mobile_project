package com.example.recipeapp.data.remote

import com.example.recipeapp.data.Recipe

fun MealDto.toRecipe(): Recipe = Recipe(
    id = idMeal.orEmpty(),
    title = strMeal.orEmpty(),
    category = strCategory.orEmpty(),
    area = strArea.orEmpty(),
    imageUrl = strMealThumb.orEmpty(),
    ingredients = ingredients(),
    instructions = strInstructions.orEmpty(),
    youtubeUrl = strYoutube.orEmpty()
)

private fun MealDto.ingredients(): List<String> {
    val pairs = listOf(
        strIngredient1 to strMeasure1,
        strIngredient2 to strMeasure2,
        strIngredient3 to strMeasure3,
        strIngredient4 to strMeasure4,
        strIngredient5 to strMeasure5,
        strIngredient6 to strMeasure6,
        strIngredient7 to strMeasure7,
        strIngredient8 to strMeasure8,
        strIngredient9 to strMeasure9,
        strIngredient10 to strMeasure10,
        strIngredient11 to strMeasure11,
        strIngredient12 to strMeasure12,
        strIngredient13 to strMeasure13,
        strIngredient14 to strMeasure14,
        strIngredient15 to strMeasure15,
        strIngredient16 to strMeasure16,
        strIngredient17 to strMeasure17,
        strIngredient18 to strMeasure18,
        strIngredient19 to strMeasure19,
        strIngredient20 to strMeasure20
    )
    return pairs.mapNotNull { (ingredient, measure) ->
        val name = ingredient?.trim().orEmpty()
        val amount = measure?.trim().orEmpty()
        when {
            name.isEmpty() -> null
            amount.isEmpty() -> name
            else -> "$amount $name"
        }
    }
}

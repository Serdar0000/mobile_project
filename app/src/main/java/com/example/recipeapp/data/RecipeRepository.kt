package com.example.recipeapp.data

import androidx.annotation.DrawableRes
import com.example.recipeapp.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object RecipeRepository {

    private val _recipes = MutableStateFlow(sampleRecipes())
    val recipes: StateFlow<List<Recipe>> = _recipes.asStateFlow()

    fun toggleFavorite(id: String) {
        _recipes.update { list ->
            list.map { recipe ->
                if (recipe.id == id) recipe.copy(isFavorite = !recipe.isFavorite) else recipe
            }
        }
    }

    private fun drawableUri(@DrawableRes res: Int): String =
        "android.resource://com.example.recipeapp/$res"

    private fun sampleRecipes(): List<Recipe> = listOf(
        Recipe(
            id = "1",
            title = "Pancakes",
            category = "Breakfast",
            area = "American",
            ingredients = listOf(
                "2 cups flour",
                "2 eggs",
                "1.5 cups milk",
                "2 tbsp sugar",
                "2 tsp baking powder",
                "2 tbsp melted butter"
            ),
            instructions = """
                Mix the flour, sugar and baking powder in a bowl.
                Whisk in the eggs, milk and melted butter until smooth.
                Heat a lightly oiled pan over medium heat.
                Pour in a small ladle of batter and flip when bubbles appear.
                Serve warm with honey or berries.
            """.trimIndent(),
            imageUrl = drawableUri(R.drawable.pancakes)
        ),
        Recipe(
            id = "2",
            title = "Shakshuka",
            category = "Breakfast",
            area = "Tunisian",
            ingredients = listOf(
                "4 eggs",
                "1 onion",
                "1 red bell pepper",
                "400 g canned tomatoes",
                "2 cloves garlic",
                "1 tsp paprika",
                "Salt and pepper"
            ),
            instructions = """
                Fry the chopped onion and pepper in oil for 5 minutes.
                Add the garlic and paprika and cook for 1 minute.
                Pour in the tomatoes and simmer for 10 minutes.
                Make 4 small wells and crack an egg into each one.
                Cover and cook until the egg whites are set.
            """.trimIndent(),
            imageUrl = drawableUri(R.drawable.shakshuka)
        ),
        Recipe(
            id = "3",
            title = "Chicken Soup",
            category = "Lunch",
            area = "American",
            ingredients = listOf(
                "500 g chicken",
                "2 potatoes",
                "1 carrot",
                "1 onion",
                "100 g noodles",
                "Salt, pepper and dill"
            ),
            instructions = """
                Boil the chicken in 2 liters of water for 25 minutes.
                Take out the chicken and cut it into pieces.
                Add the diced potatoes, carrot and onion to the broth.
                Cook for 15 minutes, then add the noodles and the chicken.
                Season with salt and pepper and add dill before serving.
            """.trimIndent(),
            imageUrl = drawableUri(R.drawable.chicken_soup)
        ),
        Recipe(
            id = "4",
            title = "Caesar Salad",
            category = "Lunch",
            area = "American",
            ingredients = listOf(
                "1 head of romaine lettuce",
                "200 g grilled chicken breast",
                "1 cup croutons",
                "50 g parmesan",
                "3 tbsp Caesar dressing"
            ),
            instructions = """
                Wash the lettuce and tear it into pieces.
                Slice the grilled chicken breast.
                Mix the lettuce with the dressing.
                Top with the chicken, croutons and grated parmesan.
            """.trimIndent(),
            imageUrl = drawableUri(R.drawable.caesar_salad)
        ),
        Recipe(
            id = "5",
            title = "Plov",
            category = "Dinner",
            area = "Uzbek",
            ingredients = listOf(
                "500 g rice",
                "500 g beef or lamb",
                "3 carrots",
                "2 onions",
                "1 head of garlic",
                "100 ml oil",
                "1 tsp cumin and salt"
            ),
            instructions = """
                Heat the oil in a kazan and fry the meat until brown.
                Add the onions and the carrots cut into strips and fry for 10 minutes.
                Add the cumin, salt and 1 liter of water and simmer for 30 minutes.
                Add the washed rice and put the whole garlic in the middle.
                Cover and cook on low heat for 30 minutes until the rice is soft.
            """.trimIndent(),
            imageUrl = drawableUri(R.drawable.plov)
        ),
        Recipe(
            id = "6",
            title = "Beshbarmak",
            category = "Dinner",
            area = "Kazakh",
            ingredients = listOf(
                "1 kg beef or horse meat",
                "2 onions",
                "500 g flour",
                "1 egg",
                "Salt and pepper"
            ),
            instructions = """
                Boil the meat in salted water for about 2 hours.
                Make a dough from the flour, egg, a little water and salt.
                Roll the dough thin and cut it into squares.
                Cook the dough squares in the meat broth for 5 minutes.
                Serve the dough with sliced meat and onions cooked in broth.
            """.trimIndent(),
            imageUrl = drawableUri(R.drawable.beshbarmak)
        ),
        Recipe(
            id = "7",
            title = "Cheesecake",
            category = "Dessert",
            area = "American",
            ingredients = listOf(
                "200 g cookies",
                "80 g butter",
                "600 g cream cheese",
                "150 g sugar",
                "3 eggs",
                "1 tsp vanilla"
            ),
            instructions = """
                Crush the cookies, mix with melted butter and press into a pan.
                Beat the cream cheese, sugar and vanilla until smooth.
                Add the eggs one by one and mix gently.
                Pour onto the base and bake at 160°C for 50 minutes.
                Let it cool and keep it in the fridge for at least 4 hours.
            """.trimIndent(),
            imageUrl = drawableUri(R.drawable.cheesecake)
        ),
        Recipe(
            id = "8",
            title = "Baursaki",
            category = "Dessert",
            area = "Kazakh",
            ingredients = listOf(
                "500 g flour",
                "250 ml warm milk",
                "1 egg",
                "1 tbsp sugar",
                "7 g dry yeast",
                "Oil for frying"
            ),
            instructions = """
                Mix the warm milk, sugar and yeast and leave for 10 minutes.
                Add the egg and flour and knead a soft dough.
                Let the dough rest for 15 minutes.
                Roll it out and cut it into small squares.
                Fry in hot oil until golden on both sides.
            """.trimIndent(),
            imageUrl = drawableUri(R.drawable.baursaki)
        )
    )
}

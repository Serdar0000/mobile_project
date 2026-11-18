# Recipe App

A simple recipe book for Android. You can see a list of recipes, search them by name, open a recipe to see its ingredients and steps, and save it to favorites. The data is kept in memory, so there is no database and no internet.

## Features

- **Home:** a list of all recipes with a photo, cooking time and category
- **Search:** filter recipes by name as you type. The text stays after screen rotation
- **Detail:** a large photo, category, time, ingredients and steps of the recipe
- **Favorites:** add or remove a recipe with one button on the Detail screen
- **Favorites screen:** only your favorite recipes, with a message when the list is empty
- **Navigation:** move between the three screens with the top bar back arrow or the system back button

## Recipes

| Recipe | Category | Time |
|---|---|---|
| Pancakes | Breakfast | 20 min |
| Shakshuka | Breakfast | 25 min |
| Chicken Soup | Lunch | 50 min |
| Caesar Salad | Lunch | 15 min |
| Plov | Dinner | 90 min |
| Beshbarmak | Dinner | 120 min |
| Cheesecake | Dessert | 80 min |
| Baursaki | Dessert | 40 min |

## Tech stack

- Kotlin
- Jetpack Compose with Material 3
- MVVM: ViewModel + StateFlow
- Navigation Compose

## Project structure

```
app/src/main/java/com/example/recipeapp/
├── MainActivity.kt
├── RecipeApp.kt            NavHost with the three screens
├── data/
│   ├── Recipe.kt           recipe data class
│   └── RecipeRepository.kt sample recipes in a StateFlow
└── ui/
    ├── components/         RecipeCard, RecipeTopAppBar
    ├── home/               HomeScreen, HomeViewModel
    ├── detail/             RecipeDetailScreen, DetailViewModel
    ├── favorites/          FavoritesScreen, FavoritesViewModel
    └── theme/              colors, typography, theme
```

## How to run

1. Open the project in Android Studio.
2. Wait for the Gradle sync to finish.
3. Run the `app` configuration on an emulator or a phone with Android 7.0 (API 24) or newer.

Or build the APK from the command line:

```
./gradlew assembleDebug
```

## Team

| Member | Part |
|---|---|
| abdyken | Project setup, data, navigation, Favorites screen |
| Serdar0000 | Home screen, search, RecipeCard |
| Yerassyl-Alimkulov | Detail screen, favorite button |

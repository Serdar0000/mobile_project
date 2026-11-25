package com.example.recipeapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.recipeapp.ui.detail.RecipeDetailScreen
import com.example.recipeapp.ui.favorites.FavoritesScreen
import com.example.recipeapp.ui.home.HomeScreen

@Composable
fun RecipeApp(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "home") {
        composable(route = "home") {
            HomeScreen(
                onRecipeClick = { id -> navController.navigate("detail/$id") },
                onFavoritesClick = { navController.navigate("favorites") }
            )
        }
        composable(
            route = "detail/{recipeId}",
            arguments = listOf(navArgument("recipeId") { type = NavType.StringType })
        ) { entry ->
            RecipeDetailScreen(
                recipeId = entry.arguments?.getString("recipeId") ?: "",
                onBack = { navController.navigateUp() }
            )
        }
        composable(route = "favorites") {
            FavoritesScreen(
                onRecipeClick = { id -> navController.navigate("detail/$id") },
                onBack = { navController.navigateUp() }
            )
        }
    }
}

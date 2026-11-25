package com.example.recipeapp.data

import com.example.recipeapp.data.local.FavoriteDao
import com.example.recipeapp.data.local.FavoriteEntity
import kotlinx.coroutines.flow.Flow

class FavoriteRepository(private val dao: FavoriteDao) {

    fun getFavorites(): Flow<List<FavoriteEntity>> = dao.getAll()

    fun isFavorite(id: String): Flow<Boolean> = dao.isFavorite(id)

    suspend fun add(recipe: Recipe) {
        dao.insert(
            FavoriteEntity(
                id = recipe.id,
                title = recipe.title,
                imageUrl = recipe.imageUrl,
                category = recipe.category
            )
        )
    }

    suspend fun updateNote(id: String, note: String, rating: Int) {
        dao.updateNote(id, note, rating)
    }

    suspend fun remove(id: String) {
        dao.delete(id)
    }
}

package com.example.recipeapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey val id: String,
    val title: String,
    val imageUrl: String,
    val category: String,
    val note: String = "",
    val rating: Int = 0,
    val addedAt: Long = System.currentTimeMillis()
)

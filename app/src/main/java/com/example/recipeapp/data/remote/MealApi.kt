package com.example.recipeapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("search.php")
    suspend fun search(@Query("s") query: String): MealsResponse

    @GET("lookup.php")
    suspend fun lookup(@Query("i") id: String): MealsResponse
}

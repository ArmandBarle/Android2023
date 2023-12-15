package com.tasty.recipesapp.api

import com.tasty.recipesapp.repository.recipe.dto.RecipeDTO
import com.tasty.recipesapp.repository.recipe.dto.RecipesDTO
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RecipeService {
    @GET("recipes/list")
    @Headers(
        "X-RapidAPI-Key: e5b7ce1c39mshcf6f0c7da3c8752p1d0302jsnecc748a7c223",
        "X-RapidAPI-Host: tasty.p.rapidapi.com"
    )
    suspend fun getRecipes(
        @Query("from") from: String,
        @Query("size") size: String,
        @Query("tags") tags: String? = null
    ): RecipesDTO

    suspend fun getRecipe(
        @Query("id") id: String
    ): RecipeDTO
}


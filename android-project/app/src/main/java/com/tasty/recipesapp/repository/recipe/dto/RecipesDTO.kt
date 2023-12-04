package com.tasty.recipesapp.repository.recipe.dto

import com.tasty.recipesapp.repository.recipe.dto.RecipeDTO

data class RecipesDTO(
    var count: Int? = null,
    var results: ArrayList<RecipeDTO> = arrayListOf()
)

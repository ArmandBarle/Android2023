package com.tasty.recipesapp.repository.recipe.model

data class RecipesDTO(
    var count: Int? = null,
    var results: ArrayList<RecipeDTO> = arrayListOf()
)

package com.tasty.recipesapp.repository.recipe.model

data class RecipeModel(
    val name: String,
    val description: String?,
    val thumbnail_url: String?,
    val instructions: List<InstructionModel>
//    val user_ratings: String?,
)
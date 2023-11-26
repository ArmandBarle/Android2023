package com.tasty.recipesapp.repository.recipe.model

data class RecipeModel(
    val id: Int,
    val name: String,
    val description: String?,
    val thumbnailUrl: String?,
    val userRatings: UserRatingsModel,
    val totalTime: TotalTimeModel,
    val instructions: List<InstructionModel>,
)
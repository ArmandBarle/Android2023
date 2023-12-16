package com.tasty.recipesapp.repository.recipe.model

data class RecipeModel(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val thumbnailUrl: String = "",
    val userRatings: UserRatingsModel = UserRatingsModel(),
    val instructions: List<InstructionsModel> = emptyList(),
    val videoUrl: String = "",
)
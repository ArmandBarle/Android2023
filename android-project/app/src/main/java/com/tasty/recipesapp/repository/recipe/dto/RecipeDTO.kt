package com.tasty.recipesapp.repository.recipe.dto

import com.tasty.recipesapp.repository.recipe.model.RecipeModel

data class RecipeDTO(
    val id: Int,
    val name: String = "",
    val description: String = "",
    val thumbnail_url: String = "",
    val user_ratings: UserRatingsDTO = UserRatingsDTO(),
//    val total_time_tier: TotalTimeTierDTO = TotalTimeTierDTO(),
    val instructions: List<InstructionsDTO> = emptyList(),
)


fun RecipeDTO.toModel() = RecipeModel(
    id = this.id,
    name = this.name,
    description = this.description,
    thumbnailUrl = this.thumbnail_url,
    userRatings = this.user_ratings.toModel(),
//    totalTime = this.total_time_tier.toModel(),
    instructions = this.instructions.toModelList()
)

fun List<RecipeDTO>.toModelList(): List<RecipeModel> {
    return this.map { it.toModel() }
}
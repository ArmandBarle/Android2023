package com.tasty.recipesapp.repository.recipe.dto

import com.tasty.recipesapp.repository.recipe.model.RecipeModel

data class RecipeDTO(
    val id: Int,
    val name: String = "",
    val description: String = "",
    val thumbnail_url: String = "",
    val user_ratings: UserRatingsDTO = UserRatingsDTO(),
    val instructions: List<InstructionsDTO> = emptyList(),
    val video_url: String? = "",
)


fun RecipeDTO.toModel() = RecipeModel(
    id = this.id,
    name = this.name,
    description = this.description,
    thumbnailUrl = this.thumbnail_url,
    userRatings = this.user_ratings.toModel(),
    instructions = this.instructions.toModelList(),
    videoUrl = this.video_url ?: ""

)

fun List<RecipeDTO>.toModelList(): List<RecipeModel> {
    return this.map { it.toModel() }
}
package com.tasty.recipesapp.repository.recipe.model

data class RecipeDTO(
    val id: Int,
    val name: String,
    val description: String? = "",
    val thumbnail_url: String?,
    val instructions: List<InstructionDTO>
//    val user_ratings: String?,
)


fun RecipeDTO.toModel() = RecipeModel(
    name = this.name,
    description = this.description,
    thumbnail_url = this.thumbnail_url,
    instructions = this.instructions.toModelList()
//    user_ratings = this.user_ratings
)

fun List<RecipeDTO>.toModelList(): List<RecipeModel> {
    return this.map { it.toModel() }
}
package com.tasty.recipesapp.repository.recipe.model

data class RecipeDTO(
    val id: Int,
    val name: String,
    val description: String? = "",
    val thumbnailUrl: String?
)


fun RecipeDTO.toModel() = RecipeModel(
    name = this.name,
    description = this.description,
    thumbnailUrl = this.thumbnailUrl
)

fun List<RecipeDTO>.toModelList(): List<RecipeModel> {
    return this.map { it.toModel() }
}
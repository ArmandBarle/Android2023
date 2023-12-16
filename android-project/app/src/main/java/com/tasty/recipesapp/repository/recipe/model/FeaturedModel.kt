package com.tasty.recipesapp.repository.recipe.model

data class FeaturedModel(
    val type: String? = null,
    val name: String? = null,
    val items: List<ItemModel> = emptyList(),
    val category: String? = null,
    val item: RecipeModel? = null,
    val minItems: Int? = null
)

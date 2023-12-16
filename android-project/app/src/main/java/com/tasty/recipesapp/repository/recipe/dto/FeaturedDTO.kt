package com.tasty.recipesapp.repository.recipe.dto

import com.tasty.recipesapp.repository.recipe.model.FeaturedModel
import com.tasty.recipesapp.repository.recipe.model.TotalTimeModel

data class FeaturedDTO(
    var type: String? = null,
    var name: String? = null,
    var items: List<ItemDTO> = emptyList(),
    var category: String? = null,
    var item: RecipeDTO? = null,
    var min_items: Int? = null
)

fun FeaturedDTO.toModel() = FeaturedModel(
    type = type,
    name = name,
    items = items.toModelList(),
    category = category,
    item = item?.toModel(),
    minItems = min_items
)

fun List<FeaturedDTO>.toModelList() = this.map { it.toModel() }
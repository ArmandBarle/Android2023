package com.tasty.recipesapp.repository.recipe.dto

import com.tasty.recipesapp.repository.recipe.model.ItemModel

data class ItemDTO(
    var id: Int? = null,
    var name: String? = null
)


fun ItemDTO.toModel() = ItemModel(
    id = id,
    name = name
)

fun List<ItemDTO>.toModelList() = this.map { it.toModel() }
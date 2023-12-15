package com.tasty.recipesapp.repository.recipe.dto

import com.tasty.recipesapp.repository.recipe.model.TotalTimeModel

data class TotalTimeTierDTO(

    var tier: String? = null,
    var displayTier: String? = null

)

fun TotalTimeTierDTO.toModel() = TotalTimeModel(
    tier = tier,
    displayTier = displayTier
)

fun List<TotalTimeTierDTO>.toModelList() = this.map { it.toModel() }
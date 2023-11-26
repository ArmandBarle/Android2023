package com.tasty.recipesapp.repository.recipe.model

data class TotalTimeTierDTO(

    var tier: String? = null,
    var displayTier: String? = null

)

fun TotalTimeTierDTO.toModel() = TotalTimeModel(
    tier = this.tier,
    displayTier = displayTier
)

fun List<TotalTimeTierDTO>.toModelList() = map { it.toModel() }
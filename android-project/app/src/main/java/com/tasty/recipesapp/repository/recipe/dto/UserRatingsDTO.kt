package com.tasty.recipesapp.repository.recipe.dto

import com.tasty.recipesapp.repository.recipe.model.UserRatingsModel

data class UserRatingsDTO(
    var countPositive: Int? = null,
    var score: Double? = null,
    var countNegative: Int? = null
)

fun UserRatingsDTO.toModel() = UserRatingsModel(
    countPositive = this.countPositive,
    score = this.score,
    countNegative = this.countNegative
)

fun List<UserRatingsDTO>.toModelList() = map { it.toModel() }
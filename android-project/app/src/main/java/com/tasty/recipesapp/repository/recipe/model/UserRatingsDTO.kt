package com.tasty.recipesapp.repository.recipe.model

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
package com.tasty.recipesapp.repository.recipe.model

data class UserRatingsModel(
    var countPositive: Int? = null,
    var score: Double? = null,
    var countNegative: Int? = null
)
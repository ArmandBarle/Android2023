package com.tasty.recipesapp.repository.recipe.dto

import com.tasty.recipesapp.repository.recipe.model.InstructionsModel

data class InstructionsDTO(
//    var appliance: String? = null,
//    var endTime: Int? = null,
//    var temperature: String? = null,
//    var id: Int? = null,
    var position: Int? = null,
    var display_text: String? = null,
//    var startTime: Int? = null
)

fun InstructionsDTO.toModel() = InstructionsModel(
//    appliance = this.appliance,
//    endTime = this.endTime,
//    temperature = this.temperature,
    position = this.position,
    displayText = this.display_text,
//    startTime = this.startTime
)

fun List<InstructionsDTO>.toModelList() = this.map { it.toModel() }
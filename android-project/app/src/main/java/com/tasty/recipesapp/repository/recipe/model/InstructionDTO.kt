package com.tasty.recipesapp.repository.recipe.model

data class InstructionDTO(
    var appliance: String? = null,
    var endTime: Int? = null,
    var temperature: String? = null,
    var id: Int? = null,
    var position: Int? = null,
    var displayText: String? = null,
    var startTime: Int? = null
)

fun InstructionDTO.toModel() = InstructionModel(
    appliance = this.appliance,
    endTime = this.endTime,
    temperature = this.temperature,
    position = this.position,
    displayText = this.displayText,
    startTime = this.startTime
)

fun List<InstructionDTO>.toModelList() = this.map { it.toModel() }
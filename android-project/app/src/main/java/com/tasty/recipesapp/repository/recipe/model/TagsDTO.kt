package com.tasty.recipesapp.repository.recipe.model

data class TagsDTO(
    var displayName: String? = null,
    var type: String? = null,
    var rootTagType: String? = null,
    var name: String? = null,
    var id: Int? = null

)

fun TagsDTO.toModel() = TagsModel(
    displayName = this.displayName,
    type = this.type,
    rootTagType = this.rootTagType,
    name = this.name,
)

fun List<TagsDTO>.toModelList() = map { it.toModel() }

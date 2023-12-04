package com.tasty.recipesapp.ui.recipe.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tasty.recipesapp.repository.recipe.RecipeRepository
import com.tasty.recipesapp.repository.recipe.model.RecipeModel

class RecipeDetailViewModel : ViewModel() {
    private val repository = RecipeRepository

    var recipe: MutableLiveData<RecipeModel> = MutableLiveData()

    fun fetchRecipeData(recipeId: Int) {
        val recipe = repository.getRecipe(recipeId)
        this.recipe.value = recipe
    }
}
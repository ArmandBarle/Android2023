package com.tasty.recipesapp.ui.recipe.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tasty.recipesapp.repository.RecipeDatabase
import com.tasty.recipesapp.repository.recipe.RecipeDao
import com.tasty.recipesapp.repository.recipe.RecipeRepository
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.ui.App

class RecipeDetailViewModel(var repository: RecipeRepository) : ViewModel() {


    var recipe: MutableLiveData<RecipeModel> = MutableLiveData()

    fun fetchRecipeData(recipeId: Int) {
        var recipe = repository.getRecipe(recipeId)

        if (recipe == null) {
            recipe = repository.getMyRecipe(recipeId)
        }

        Log.d("xyz", "fetchRecipeData: $recipe")
        this.recipe.value = recipe
    }

    fun fetchRecipeDataFromAPI(recipeId: Int) {
        val recipe = repository.getRecipeFromAPI(recipeId.toString())
        Log.d("xyz", "fetchRecipeDataFromAPI: $recipe")
        this.recipe.value = recipe
    }
}
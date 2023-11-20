package com.tasty.recipesapp.ui.recipe.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tasty.recipesapp.repository.recipe.RecipeRepository
import com.tasty.recipesapp.repository.recipe.model.RecipeModel

class RecipeListViewModel : ViewModel() {

    private val repository = RecipeRepository

    var recipesList: MutableLiveData<List<RecipeModel>> = MutableLiveData()

    fun fetchRecipesData(context: Context) {
        recipesList.value = repository.getRecipes(context)
    }
}
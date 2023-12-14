package com.tasty.recipesapp.ui.recipe.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tasty.recipesapp.repository.recipe.RecipeDao
import com.tasty.recipesapp.repository.recipe.RecipeRepository
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.ui.App

class RecipeListViewModel(var repository: RecipeRepository) : ViewModel() {

//    private val repository = RecipeRepository()
//    private val repository = (App.getAppContext() as App).repository

    var recipesList: MutableLiveData<List<RecipeModel>> = MutableLiveData()

    fun fetchRecipesData(context: Context) {
        recipesList.value = repository.getRecipes(context)
    }
}
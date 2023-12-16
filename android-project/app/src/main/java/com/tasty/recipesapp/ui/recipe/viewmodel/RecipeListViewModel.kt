package com.tasty.recipesapp.ui.recipe.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.repository.recipe.RecipeDao
import com.tasty.recipesapp.repository.recipe.RecipeRepository
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.ui.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeListViewModel(var repository: RecipeRepository) : ViewModel() {

    var recipesList: MutableLiveData<List<RecipeModel>> = MutableLiveData()

    // Json

//    fun fetchRecipesData(context: Context) {
//        recipesList.value = repository.getRecipes(context)
//    }

    //API

    fun getAllRecipesFromApi() {
        viewModelScope.launch {
            val recipes = withContext(Dispatchers.IO) {
                repository.getRecipesFromAPI("0", "40")
            }
            Log.d("xyz", "getAllRecipesFromApi: $recipes")
            recipesList.value = recipes
        }
    }


}
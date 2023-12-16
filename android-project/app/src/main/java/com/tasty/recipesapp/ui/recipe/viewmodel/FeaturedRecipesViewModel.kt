package com.tasty.recipesapp.ui.recipe.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.repository.recipe.RecipeRepository
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FeaturedRecipesViewModel(var repository: RecipeRepository) : ViewModel() {
    var recipesList: MutableLiveData<List<RecipeModel>> = MutableLiveData()

    fun getFeaturedRecipesFromApi() {
        viewModelScope.launch {
            val recipes = withContext(Dispatchers.IO) {
                repository.getFeedRecipesFromAPI("2", "+0700", "false", "0")
            }
            Log.d("xyz", "getAllRecipesFromApi: $recipes")
            recipesList.value = recipes
        }
    }
}
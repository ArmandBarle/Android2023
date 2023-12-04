package com.tasty.recipesapp.ui.profile.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.repository.recipe.RecipeRepository
import com.tasty.recipesapp.repository.recipe.model.RecipeEntity
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel(private val repository: RecipeRepository) : ViewModel() {

    var myRecipesList: MutableLiveData<List<RecipeModel>> = MutableLiveData()

    var insertResult: MutableLiveData<Boolean> = MutableLiveData()

    var deleteResult: MutableLiveData<Boolean> = MutableLiveData()

    fun fetchMyRecipesData() {
        val recipes = repository.getMyRecipes()
        myRecipesList.value = recipes
    }

    // List
    fun insertRecipe(recipe: RecipeModel) {
        viewModelScope.launch {
            val result = repository.insertRecipe(recipe)
            insertResult.value = result
        }
    }

    fun deleteRecipe(recipe: RecipeModel) {
        val result = repository.deleteRecipe(recipe)
        deleteResult.value = result
    }


    // Database

    fun insertRecipe(recipe: RecipeEntity) {
        viewModelScope.launch {
            val isSuccessful = withContext(Dispatchers.IO) {
                repository.insertRecipe(recipe)
            }
            insertResult.value = isSuccessful
        }
    }

    fun deleteRecipe(recipe: RecipeEntity) {
        viewModelScope.launch {
            val isSuccessful = withContext(Dispatchers.IO) {
                repository.deleteRecipe(recipe)
            }
            deleteResult.value = isSuccessful
        }
    }

    fun getMyRecipes() {
        viewModelScope.launch {
            val myRecipes = withContext(Dispatchers.IO) {
                repository.getAllRecipes()
            }
            myRecipesList.value = myRecipes
        }
    }

}
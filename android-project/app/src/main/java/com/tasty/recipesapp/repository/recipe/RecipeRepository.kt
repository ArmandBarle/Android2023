package com.tasty.recipesapp.repository.recipe

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.repository.recipe.dto.RecipesDTO
import com.tasty.recipesapp.repository.recipe.dto.toModelList
import java.io.IOException

object RecipeRepository {

    private val TAG: String? = RecipeRepository::class.java.canonicalName
    private var recipesList: List<RecipeModel> = emptyList()

    fun getRecipes(context: Context): List<RecipeModel> {
        lateinit var jsonString: String
        try {
            jsonString =
                context.assets.open("recipes.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            Log.e(TAG, "getRecipes: Error ", ioException)
        }

        val recipesResponse: RecipesDTO =
            Gson().fromJson(jsonString, object : TypeToken<RecipesDTO>() {}.type)

        recipesList = recipesResponse.results.toModelList()

        return recipesList
    }

    fun getRecipe(recipeId: Int): RecipeModel? = recipesList.find { it.id == recipeId }

//    fun insertRecipe(recipe: RecipeModel): Boolean {
//        return myRecipeList.add(recipe)
//    }
//
//    fun deleteRecipe(recipe: RecipeModel): Boolean {
//        return myRecipeList.remove(recipe)
//    }
//
//    fun getMyRecipes(context: Context)= myRecipeList
}


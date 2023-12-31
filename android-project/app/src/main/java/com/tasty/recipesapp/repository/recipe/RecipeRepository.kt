package com.tasty.recipesapp.repository.recipe

import android.content.Context
import android.util.Log
import androidx.room.Dao
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.api.RecipeApiClient
import com.tasty.recipesapp.repository.recipe.dto.RecipeDTO
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.repository.recipe.dto.RecipesDTO
import com.tasty.recipesapp.repository.recipe.dto.toModel
import com.tasty.recipesapp.repository.recipe.dto.toModelList
import com.tasty.recipesapp.repository.recipe.model.RecipeEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.IOException

class RecipeRepository(private val recipeDao: RecipeDao) {

    private val TAG: String? = RecipeRepository::class.java.canonicalName
    private var recipesList: List<RecipeModel> = emptyList()
    private var myRecipesList: ArrayList<RecipeModel> = ArrayList()
    private val recipeApiClient = RecipeApiClient()
    private val featuredList = ArrayList<RecipeModel>()

    // API
    suspend fun getRecipesFromAPI(
        from: String,
        size: String,
        tags: String? = null,
    ): List<RecipeModel> {
        recipesList = recipeApiClient.recipeService
            .getRecipes(from, size, tags).results.toModelList()
        return recipesList
    }

    suspend fun getFeedRecipesFromAPI(
        size: String,
        timezone: String,
        vegetarian: String? = "false",
        from: String? = "0"
    ): List<RecipeModel> {
        val fs = recipeApiClient.recipeService
            .getFeed(size, timezone, vegetarian, from).results.toModelList()

        Log.d("xyz", "fs: $fs")
        for (f in fs) {
            if (f.type == "item") {
                featuredList.add(f.item!!)
            }
        }

        return featuredList
    }

    // JSON
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

    //API
    fun getRecipeFromAPI(
        id: String
    ): RecipeModel {
        val recipe = recipeApiClient.recipeService.getRecipe(id)
        Log.d("xyz", "getRecipeFromAPI: $recipe")
        return recipe.toModel()
    }

    // List
    fun getMyRecipe(recipeId: Int): RecipeModel? = myRecipesList.find { it.id == recipeId }
    fun insertRecipe(recipe: RecipeModel): Boolean {
        return myRecipesList.add(recipe)
    }

    fun deleteRecipe(recipe: RecipeModel): Boolean {
        return myRecipesList.remove(recipe)
    }

    fun getMyRecipes() = myRecipesList


// Database

    suspend fun insertRecipe(recipe: RecipeEntity) {
        val result = recipeDao.insertRecipe(recipe)
        Log.d("xyz", "insertRecipe: $result")
    }

    suspend fun deleteRecipe(recipe: RecipeEntity) {
        val result = recipeDao.deleteRecipe(recipe)
        Log.d("xyz", "deleteRecipe: $result")
    }

    suspend fun getAllRecipes(): List<RecipeModel> {
        return recipeDao.getAllRecipes().map {
            val jsonObject = JSONObject(it.json)
            jsonObject.apply { put("id", it.internalId) }
            Gson().fromJson(jsonObject.toString(), RecipeDTO::class.java).toModel()
        }
    }
}



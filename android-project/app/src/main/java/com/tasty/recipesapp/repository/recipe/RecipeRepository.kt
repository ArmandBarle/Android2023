package com.tasty.recipesapp.repository.recipe

import android.content.Context
import android.util.Log
import androidx.room.Dao
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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
//object RecipeRepository {

    private val TAG: String? = RecipeRepository::class.java.canonicalName
    private var recipesList: List<RecipeModel> = emptyList()
    private var myRecipesList: ArrayList<RecipeModel> = ArrayList()

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

    // List
    fun insertRecipe(recipe: RecipeModel): Boolean {
        return myRecipesList.add(recipe)
    }

    fun deleteRecipe(recipe: RecipeModel): Boolean {
        return myRecipesList.remove(recipe)
    }

    fun getMyRecipes() = myRecipesList


    // Database
    // Todo why boolean

    suspend fun insertRecipe(recipe: RecipeEntity): Boolean {
        val result = recipeDao.insertRecipe(recipe)
        Log.d("xyz", "insertRecipe: $result")

//        return result > 0
        return true
    }

    suspend fun deleteRecipe(recipe: RecipeEntity): Boolean {
        val result = recipeDao.deleteRecipe(recipe)
        Log.d("xyz", "deleteRecipe: $result")
//        return result > 0
        return true
    }

    suspend fun getAllRecipes(): List<RecipeModel> {
        return recipeDao.getAllRecipes().map {
            val jsonObject = JSONObject(it.json)
            jsonObject.apply { put("id", it.internalId) }
            Gson().fromJson(jsonObject.toString(), RecipeDTO::class.java).toModel()
        }
    }
}



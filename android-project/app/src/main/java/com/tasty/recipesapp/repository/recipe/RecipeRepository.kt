package com.tasty.recipesapp.repository.recipe

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.repository.recipe.model.RecipesDTO
import java.io.IOException

object RecipeRepository {

    private val TAG: String? = RecipeRepository::class.java.canonicalName

    fun getReciepes(context: Context): List<RecipeModel> {
        lateinit var jsonString: String
        try {
            jsonString =
                context.assets.open("reciepes.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            Log.e(TAG, "getReciepes: Error ", ioException)
        }

        val recipesResponse: RecipesDTO =
            Gson().fromJson(jsonString, object:TypeToken<RecipesDTO>(){}.type)

        return recipesResponse.results.toModelList()

    }

}


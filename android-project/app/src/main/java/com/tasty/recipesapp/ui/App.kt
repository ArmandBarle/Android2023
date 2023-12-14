package com.tasty.recipesapp.ui

import android.app.Application
import android.content.Context
import com.tasty.recipesapp.repository.RecipeDatabase
import com.tasty.recipesapp.repository.recipe.RecipeRepository

class App: Application() {

//    companion object {
//        private lateinit var instance: App
//
//        fun getAppContext(): Context {
//            return instance.applicationContext
//        }
//    }

    private val database by lazy { RecipeDatabase.getDatabase(this) }
    val repository by lazy { RecipeRepository(database.recipeDao()) }

}
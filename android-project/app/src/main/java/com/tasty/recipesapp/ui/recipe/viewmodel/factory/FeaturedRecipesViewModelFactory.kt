package com.tasty.recipesapp.ui.recipe.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tasty.recipesapp.repository.recipe.RecipeRepository
import com.tasty.recipesapp.ui.recipe.viewmodel.FeaturedRecipesViewModel

class FeaturedRecipesViewModelFactory
    (private val repository: RecipeRepository) : ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FeaturedRecipesViewModel(repository) as T
    }
}
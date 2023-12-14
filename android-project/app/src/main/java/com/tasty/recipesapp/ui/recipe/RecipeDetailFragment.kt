package com.tasty.recipesapp.ui.recipe

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentRecipeDetailBinding
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.ui.App
import com.tasty.recipesapp.ui.recipe.viewmodel.RecipeDetailViewModel
import com.tasty.recipesapp.ui.recipe.viewmodel.RecipeListViewModel
import com.tasty.recipesapp.ui.recipe.viewmodel.factory.RecipeDetailViewModelFactory
import com.tasty.recipesapp.ui.recipe.viewmodel.factory.RecipeViewModelFactory


class RecipeDetailFragment : Fragment() {

    private lateinit var binding: FragmentRecipeDetailBinding
    private lateinit var viewModel: RecipeDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recipeId = arguments?.getInt(RecipesFragment.BUNDLE_EXTRA_SELECTED_RECIPE_ID)

        Log.d(TAG, "show details of recipe with id: $recipeId")

//        val viewModel = ViewModelProvider(this)[RecipeDetailViewModel::class.java]

        val factory = RecipeDetailViewModelFactory((activity?.application as App).repository)
        viewModel = ViewModelProvider(this, factory)[RecipeDetailViewModel::class.java]

        recipeId?.let { viewModel.fetchRecipeData(it) }

        viewModel.recipe.observe(viewLifecycleOwner) {
            Log.d(TAG, "show details of recipe with id: $it")
            updateViews(it)
        }
    }

    private fun updateViews(recipeModel: RecipeModel) {
        binding.recipeTitleView.text = recipeModel.name
        binding.recipeDescriptionView.text = recipeModel.description

        context?.let {
            Glide.with(it)
                .load(recipeModel.thumbnailUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .fallback(R.drawable.ic_launcher_background)
                .into(binding.recipeImageView)
        }

        val ratingsLabel = requireActivity().getString(R.string.user_ratings_label)

        binding.recipeRatingsView.text = ratingsLabel.plus(" ").plus(recipeModel.userRatings.score)

        binding.recipeTotalTimeView.text = recipeModel.totalTime.displayTier

        val instructionsString = recipeModel.instructions.joinToString("\n") {
            it.position.toString().plus(". ").plus(it.displayText)
        }

        binding.recipeInstructionsView.text = instructionsString
    }


}
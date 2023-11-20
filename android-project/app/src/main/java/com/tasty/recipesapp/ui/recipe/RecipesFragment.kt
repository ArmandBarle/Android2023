package com.tasty.recipesapp.ui.recipe

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentRecipesBinding
import com.tasty.recipesapp.repository.recipe.RecipeRepository
import com.tasty.recipesapp.ui.recipe.adapter.RecipeListAdapter
import com.tasty.recipesapp.ui.recipe.viewmodel.RecipeListViewModel


class RecipesFragment : Fragment() {

    companion object {
        private val TAG: String? = RecipesFragment::class.java.canonicalName
        const val BUNDLE_EXTRA_SELECTED_RECIPE_ID = "selected_recipe_id"
    }

    private lateinit var binding: FragmentRecipesBinding
    private lateinit var recipesAdapter: RecipeListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRecipesBinding.inflate(inflater, container, false)
        initRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this).get(RecipeListViewModel::class.java)

        context?.let {
            viewModel.fetchRecipesData(it)
        }

        viewModel.recipesList.observe(viewLifecycleOwner) { recipes ->
//            for (recipe in recipes) {
//                Log.d(TAG, "Recipe Name: ${recipe.name}")
//                Log.d(TAG, "Recipe Description: ${recipe.description}")
//                Log.d(TAG, "Recipe thumbnail: ${recipe.thumbnail_url}")
////                Log.d(TAG, "Recipe thumbnail: ${recipe.user_ratings}")
//                Log.d(TAG, "Recipe instructions:")
//                for (instruciton in recipe.instructions) {
//                    Log.d(TAG, "display text: ${instruciton.displayText}")
//                }
//                Log.d(TAG, "------------------------------------------------")
//            }

        }
    }

    private fun initRecyclerView() {
        recipesAdapter = RecipeListAdapter(ArrayList(),requireContext(),
            onItemClickListener =
            { recipe ->
                navigateToRecipeDetails(recipe)
            })

        binding.recyclerView.adapter = recipesAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL))
    }
}
package com.tasty.recipesapp.ui.recipe

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentRecipesBinding
import com.tasty.recipesapp.repository.recipe.RecipeRepository
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.ui.App
import com.tasty.recipesapp.ui.profile.viewmodel.ProfileViewModel
import com.tasty.recipesapp.ui.profile.viewmodel.factory.ProfileViewModelFactory
import com.tasty.recipesapp.ui.recipe.adapter.RecipeListAdapter
import com.tasty.recipesapp.ui.recipe.viewmodel.RecipeListViewModel
import com.tasty.recipesapp.ui.recipe.viewmodel.factory.RecipeViewModelFactory


class RecipesFragment : Fragment() {

    companion object {
        private val TAG: String? = RecipesFragment::class.java.canonicalName
        const val BUNDLE_EXTRA_SELECTED_RECIPE_ID = "selected_recipe_id"
    }

    private lateinit var binding: FragmentRecipesBinding
    private lateinit var recipesAdapter: RecipeListAdapter
    private lateinit var viewModel: RecipeListViewModel

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

        Log.d(TAG, "onViewCreated: ")
//        val viewModel = ViewModelProvider(this).get(RecipeListViewModel::class.java)

        val factory = RecipeViewModelFactory((activity?.application as App).repository)
        viewModel = ViewModelProvider(this, factory)[RecipeListViewModel::class.java]

        //Json
//        context?.let {
//            viewModel.fetchRecipesData(it)
//        }

        //API
        viewModel.getAllRecipesFromApi()

        viewModel.recipesList.observe(viewLifecycleOwner) { recipes ->
            recipesAdapter.setData(recipes)
            recipesAdapter.notifyItemRangeChanged(0, recipes.lastIndex)
        }
    }

    private fun initRecyclerView() {
        recipesAdapter = RecipeListAdapter(ArrayList(), requireContext(),
            onItemClickListener =
            { recipe ->
                navigateToRecipeDetails(recipe)
            })

        binding.recyclerView.adapter = recipesAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun navigateToRecipeDetails(recipe: RecipeModel) {
        findNavController().navigate(
            R.id.action_recipesFragment_to_recipeDetailFragment,
            bundleOf(
                BUNDLE_EXTRA_SELECTED_RECIPE_ID to recipe.id
            )
        )
    }
}
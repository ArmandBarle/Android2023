package com.tasty.recipesapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentHomeBinding
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.ui.App
import com.tasty.recipesapp.ui.recipe.RecipesFragment
import com.tasty.recipesapp.ui.recipe.adapter.RecipeListAdapter
import com.tasty.recipesapp.ui.recipe.viewmodel.FeaturedRecipesViewModel
import com.tasty.recipesapp.ui.recipe.viewmodel.factory.FeaturedRecipesViewModelFactory


class HomeFragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding
    private lateinit var recipesAdapter: RecipeListAdapter
    private lateinit var viewModel: FeaturedRecipesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        initRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val factory = FeaturedRecipesViewModelFactory((activity?.application as App).repository)
        viewModel = ViewModelProvider(this, factory)[FeaturedRecipesViewModel::class.java]

        //API
        viewModel.getFeaturedRecipesFromApi()

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

        binding.featureRecipesRecycleView.adapter = recipesAdapter
        binding.featureRecipesRecycleView.layoutManager = LinearLayoutManager(context)
        binding.featureRecipesRecycleView.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun navigateToRecipeDetails(recipe: RecipeModel) {
        findNavController().navigate(
            R.id.action_homeFragment_to_recipeDetailFragment,
            bundleOf(
                RecipesFragment.BUNDLE_EXTRA_SELECTED_RECIPE_ID to recipe.id
            )
        )
    }
}
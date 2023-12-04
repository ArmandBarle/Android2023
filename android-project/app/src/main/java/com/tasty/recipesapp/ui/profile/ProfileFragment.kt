package com.tasty.recipesapp.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentProfileBinding
import com.tasty.recipesapp.databinding.FragmentRecipesBinding
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.ui.profile.viewmodel.ProfileViewModel
import com.tasty.recipesapp.ui.recipe.RecipesFragment
import com.tasty.recipesapp.ui.recipe.adapter.RecipeListAdapter
import com.tasty.recipesapp.ui.recipe.viewmodel.RecipeListViewModel

class ProfileFragment : Fragment() {

    companion object {
        private val TAG: String? = ProfileFragment::class.java.canonicalName
        const val BUNDLE_EXTRA_SELECTED_RECIPE_ID = "selected_recipe_id"
    }

    private lateinit var binding: FragmentProfileBinding
    private lateinit var recipesAdapter: RecipeListAdapter
    private lateinit var viewModel: ProfileViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        initRecyclerView()

        binding.newRecipeButton.setOnClickListener {
            navigateToNewRecipe()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ProfileViewModelFactory((activity?.application as App).repository)
        viewModel = ViewModelProvider(this, factory)[ProfileViewModel::class.java]


        viewModel.fetchMyRecipesData()


        viewModel.myRecipesList.observe(viewLifecycleOwner) { myRecipes ->
            recipesAdapter.setData(myRecipes)
            recipesAdapter.notifyItemRangeChanged(0, myRecipes.lastIndex)
        }

        viewModel.deleteResult.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(context, "Recipe deleted", Toast.LENGTH_SHORT).show()
                recipesAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(context, "Recipe was not deleted", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initRecyclerView() {
        recipesAdapter = RecipeListAdapter(ArrayList(), requireContext(),
            onItemClickListener =
            { recipe ->
                navigateToRecipeDetails(recipe)
            },
            onItemLongClickListener = { recipe ->
                viewModel.deleteRecipe(recipe)
            })

        binding.recyclerProfileView.adapter = recipesAdapter
        binding.recyclerProfileView.layoutManager = LinearLayoutManager(context)
        binding.recyclerProfileView.addItemDecoration(
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

    private fun navigateToNewRecipe() {
        findNavController().navigate(
            R.id.action_profileFragment_to_newRecipeFragment
        )
    }

}
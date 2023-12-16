package com.tasty.recipesapp.ui.recipe

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.tasty.recipesapp.databinding.FragmentNewRecipeBinding
import com.tasty.recipesapp.repository.recipe.model.InstructionsModel
import com.tasty.recipesapp.repository.recipe.model.RecipeEntity
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.repository.recipe.model.UserRatingsModel
import com.tasty.recipesapp.ui.App
//import com.tasty.recipesapp.ui.App
import com.tasty.recipesapp.ui.profile.viewmodel.ProfileViewModel
import com.tasty.recipesapp.ui.profile.viewmodel.factory.ProfileViewModelFactory
import com.tasty.recipesapp.ui.recipe.viewmodel.RecipeListViewModel
import kotlin.math.log

class NewRecipeFragment : Fragment() {

    private lateinit var binding: FragmentNewRecipeBinding
    private var editTextCount = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNewRecipeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ProfileViewModelFactory((activity?.application as App).repository)
        val viewModel = ViewModelProvider(this, factory)[ProfileViewModel::class.java]

        val addButton: Button = binding.addInstructionButton
        addButton.setOnClickListener { onAddEditTextClick() }

        binding.newRecipeSaveButton.setOnClickListener {
            val recipeModel = RecipeModel(
                viewModel.repository.getMyRecipes().size + 1,
                binding.newRecipeNameView.text.toString(),
                binding.newRecipeDescriptionView.text.toString(),
                binding.newRecipeImageUrlView.text.toString(),
                UserRatingsModel(0, 0.0, 0),
                emptyList<InstructionsModel>(),
                binding.newRecipeVideoUrlView.text.toString(),
            )

//            val gson = Gson()
//            val jsonString = gson.toJson(recipeModel)
//            val recipeEntity = RecipeEntity(json = jsonString)
//
//
//            Log.d(TAG, "Recipe: $recipeEntity")
//            viewModel.insertRecipe(recipeEntity)
            viewModel.insertRecipe(recipeModel)
        }

        viewModel.insertResult.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(context, "Recipe inserted", Toast.LENGTH_SHORT).show()
                navigateBack()
            } else {
                Toast.makeText(context, "Recipe was not inserted", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateBack() {
        findNavController().popBackStack()
    }

    fun onAddEditTextClick() {
        val container: LinearLayout = binding.root

        Log.d("TAG", "onAddEditTextClick: $editTextCount")
        // Create a new EditText
        val newEditText = EditText(requireContext())
        newEditText.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )


        // Increment the count to maintain unique IDs
        editTextCount++
        newEditText.hint = "Intruction"

        newEditText.id = View.generateViewId()

        // Add the new EditText to the container
        container.addView(newEditText)
    }
}
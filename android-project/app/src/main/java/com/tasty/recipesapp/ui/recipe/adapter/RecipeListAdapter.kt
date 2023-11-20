package com.tasty.recipesapp.ui.recipe.adapter

import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.RecipeListItemBinding
import com.tasty.recipesapp.repository.recipe.model.RecipeModel

class RecipeListAdapter(
    private var recipesList: List<RecipeModel>,
    private val context: Context
) : RecyclerView.Adapter<RecipeListAdapter.RecipeItemViewHolder>() {

    inner class RecipeItemViewHolder(binding: RecipeListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val recipeTitleView: TextView = binding.recipeItemTitleView
        val recipeDescriptionView: TextView = binding.recipeItemDescriptionView
        val recipeImageView: ImageView = binding.recipeItemImageView
        //
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeItemViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = recipesList.size

    override fun onBindViewHolder(holder: RecipeItemViewHolder, position: Int) {
        val currentRecipe = recipesList[position]

        holder.recipeTitleView.text = currentRecipe.name
        holder.recipeDescriptionView.text = currentRecipe.description

        Glide.with(context)
            .load(currentRecipe.thumbnail_url)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .fallback(R.drawable.ic_launcher_background)
            .into(holder.recipeImageView)
    }

    fun setData(newList: List<RecipeModel>) {
        recipesList = newList
    }
}


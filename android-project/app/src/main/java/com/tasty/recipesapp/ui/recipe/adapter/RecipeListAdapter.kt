package com.tasty.recipesapp.ui.recipe.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
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
    private val context: Context,
    private val onItemClickListener: (RecipeModel) -> Unit,
    private val onItemLongClickListener: (RecipeModel) -> Unit = {},
) : RecyclerView.Adapter<RecipeListAdapter.RecipeItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeItemViewHolder {
        val binding = RecipeListItemBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
        return RecipeItemViewHolder(binding)
    }

    override fun getItemCount(): Int = recipesList.size

    override fun onBindViewHolder(holder: RecipeItemViewHolder, position: Int) {
        val currentRecipe = recipesList[position]

        holder.recipeTitleView.text = currentRecipe.name
        holder.recipeDescriptionView.text = currentRecipe.description


        Log.d("TAG", "onBindViewHolder: ${currentRecipe.thumbnailUrl}")
        Glide.with(context)
            .load(currentRecipe.thumbnailUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .fallback(R.drawable.ic_launcher_background)
            .into(holder.recipeImageView)

//        val ratingsLabel = "Rating:"
//        holder.recipeRatingView.text = ratingsLabel
//            .plus(" ").plus(currentRecipe.userRatings.score)
    }

    fun setData(newList: List<RecipeModel>) {
        recipesList = newList
    }

    inner class RecipeItemViewHolder(binding: RecipeListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val recipeTitleView: TextView = binding.recipeItemTitleView
        val recipeDescriptionView: TextView = binding.recipeItemDescriptionView
        val recipeImageView: ImageView = binding.recipeItemImageView
        val recipeRatingView: TextView = binding.recipeItemRatingView

        init {
            binding.root.setOnClickListener {
                val currentPosition = this.adapterPosition
                val currentRecipe = recipesList[currentPosition]

                onItemClickListener(currentRecipe)
            }

            binding.root.setOnLongClickListener {
                val currentPosition = this.adapterPosition
                val currentRecipe = recipesList[currentPosition]
                onItemLongClickListener(currentRecipe)
                true
            }
        }
    }
}


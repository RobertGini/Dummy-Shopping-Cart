package com.example.feature_catalogue.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.example.core.databinding.ItemCategoriesBinding
import com.example.core.databinding.ItemListBinding
import com.example.core.domain.interfaces.OnCategoryClick
import com.example.data_products.domain.model.CategoryEntity

class CategoryAdapter(
    private val onCategoryClick: OnCategoryClick<View, CategoryEntity>
): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val items = ArrayList<CategoryEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoriesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class CategoryViewHolder(
        private val binding: ItemCategoriesBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(entity: CategoryEntity)  {
            binding.apply {
                rcTitle.text = entity.category_name
                rcImage.load(
                    entity.category_image
                ) {
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }
                itemContainer.setOnClickListener {
                    onCategoryClick.onCategoryClick(it, entity)
                }
            }

        }
    }

    fun setItems(data: List<CategoryEntity>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }
}
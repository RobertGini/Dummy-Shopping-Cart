package com.example.dummyshoppingcart.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.example.dummyshoppingcart.databinding.ItemListBinding
import com.example.dummyshoppingcart.domain.interfaces.OnCategoryClick
import com.example.dummyshoppingcart.domain.interfaces.OnProductClick
import com.example.dummyshoppingcart.domain.model.CategoryEntity

class CategoryAdapter(
    private val onCategoryClick: OnCategoryClick
): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val items = ArrayList<CategoryEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemListBinding.inflate(
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
        private val binding: ItemListBinding
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
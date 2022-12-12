package com.example.feature_details.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.example.core.domain.interfaces.OnProductClick
import com.example.core.domain.interfaces.ToCartClick
import com.example.feature_details.databinding.ProductByListBinding
import com.example.feature_details.domain.model.DetailsEntity

class ProductByAdapter(
    private val onProductClick: OnProductClick<View, DetailsEntity>,
    private val toCartClick: ToCartClick<View, DetailsEntity>
): RecyclerView.Adapter<ProductByAdapter.ProductViewHolder>() {

    private val items = ArrayList<DetailsEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductByListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class ProductViewHolder(
        private val binding: ProductByListBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(entity: DetailsEntity)  {
            binding.apply {
                rcCategoryTitle.text = entity.details_title
                rcCategoryImage.load(
                    entity.details_images[0]
                ) {
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }
                itemContainer.setOnClickListener{
                    onProductClick.onProductClicked(it, entity)
                }
                buttonToCart.setOnClickListener {
                    toCartClick.toCartClicked(it, entity)
                }
            }
        }
    }

    fun setItems(data: List<DetailsEntity>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }
}
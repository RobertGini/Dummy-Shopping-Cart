package com.example.feature_details.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.example.core.domain.interfaces.OnProductClick
import com.example.core.domain.interfaces.ToCartClick
import com.example.data_details.domain.model.DetailsEnitiy
import com.example.feature_details.databinding.ProductByListBinding

class ProductByAdapter(
    private val onProductClick: OnProductClick<View, DetailsEnitiy>,
    private val toCartClick: ToCartClick<View, DetailsEnitiy>
): RecyclerView.Adapter<ProductByAdapter.ProductViewHolder>() {

    private val items = ArrayList<DetailsEnitiy>()

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
        fun bind(entity: DetailsEnitiy)  {
            binding.apply {
                rcCategoryTitle.text = entity.details_title
                rcCategoryImage.load(
                    entity.details_images?.get(0)
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

    fun setItems(data: List<DetailsEnitiy>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }
}
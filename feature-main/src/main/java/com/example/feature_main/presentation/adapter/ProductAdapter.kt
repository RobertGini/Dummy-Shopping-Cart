package com.example.dummyshoppingcart.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.example.core.databinding.ItemListBinding
import com.example.core.domain.interfaces.OnProductClick
import com.example.dummyshoppingcart.domain.model.ProductEntity

class ProductAdapter(
    private val onProductClick: OnProductClick<View, ProductEntity>
): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private val items = ArrayList<ProductEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemListBinding.inflate(
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
        private val binding: ItemListBinding
        ): RecyclerView.ViewHolder(binding.root) {
        fun bind(entity: ProductEntity)  {
            binding.apply {
                rcTitle.text = entity.product_title
                rcPrice.text = entity.product_price
                rcImage.load(
                    entity.product_images[0]
                ) {
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }
                itemContainer.setOnClickListener{
                    onProductClick.onProductClicked(it, entity)
                }
            }
        }
    }

    fun setItems(data: List<ProductEntity>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }
}

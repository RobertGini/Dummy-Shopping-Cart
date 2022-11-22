package com.example.dummyshoppingcart.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.example.dummyshoppingcart.R
import com.example.dummyshoppingcart.databinding.ItemListBinding
import com.example.dummyshoppingcart.domain.model.ProductEntity

class ProductAdapter: RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private val items = ArrayList<ProductEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ProductViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(entity: ProductEntity)  {
            binding.apply {
                rcTitle.text = entity.product_title
                rcPrice.text = entity.product_price
                rcImage.load(
                    entity.product_images[0]
                ) {
                    crossfade(true)
                    placeholder(R.mipmap.ic_launcher)
                    transformations(CircleCropTransformation())
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

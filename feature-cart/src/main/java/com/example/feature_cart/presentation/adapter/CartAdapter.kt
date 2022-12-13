package com.example.feature_cart.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.example.core.domain.interfaces.OnProductClick
import com.example.data_details.domain.model.DetailsEnitiy
import com.example.feature_cart.databinding.ItemCartBinding

class CartAdapter(
    private val onProductClick: OnProductClick<View, DetailsEnitiy>
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private val items = ArrayList<DetailsEnitiy>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size


    inner class CartViewHolder(
        private val binding: ItemCartBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entity: DetailsEnitiy) {
            binding.apply {
                cartTitle.text = entity.details_title
                cartPrice.text = entity.details_price
                cartImage.load(
                    entity.details_images[0]
                ) {
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }
                cartContainer.setOnClickListener {
                    onProductClick.onProductClicked(it, entity)
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
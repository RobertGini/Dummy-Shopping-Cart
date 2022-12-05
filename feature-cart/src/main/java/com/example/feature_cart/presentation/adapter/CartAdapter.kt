package com.example.feature_cart.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.example.feature_cart.data.model.Cart
import com.example.feature_cart.databinding.ItemCartBinding

class CartAdapter : ListAdapter<Cart, CartAdapter.CartHolder>(CartComparator()) {

    class CartHolder(
        private val binding: ItemCartBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cart: Cart) {
            binding.apply {
                cartTitle.text = cart.cart_title
                cartPrice.text = cart.cart_price
                cartImage.load(
                    cart.cart_images[0]
                ) {
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }
            }
        }

        companion object {
            fun create(parent: ViewGroup): CartHolder {
                return CartHolder(
                    ItemCartBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
        }
    }

    class CartComparator : DiffUtil.ItemCallback<Cart>() {
        override fun areItemsTheSame(oldItem: Cart, newItem: Cart): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Cart, newItem: Cart): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartHolder {
        return CartHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CartHolder, position: Int) {
        holder.bind(getItem(position))
    }

}
package com.example.dummyshoppingcart.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dummyshoppingcart.databinding.ItemListBinding
import com.example.dummyshoppingcart.domain.model.DelegateAdapterItem
import com.example.dummyshoppingcart.domain.model.ProductEntity

class MainAdapter :
    DelegateAdapter<ProductEntity, MainAdapter.MainViewHolder>(ProductEntity::class.java) {
    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun bindViewHolder(
        model: ProductEntity,
        viewHolder: MainViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(model)
    }

    inner class MainViewHolder(
        private val binding: ItemListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entity: ProductEntity) = with(binding) {
            rcTitle.text = entity.product_title
            rcPrice.text = entity.product_price
        }
    }
}



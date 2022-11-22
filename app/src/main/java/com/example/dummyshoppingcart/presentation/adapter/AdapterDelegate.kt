package com.example.dummyshoppingcart.presentation.adapter

import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dummyshoppingcart.databinding.ItemPromoBinding
import com.example.dummyshoppingcart.domain.iterators.DisplayableItem
import com.example.dummyshoppingcart.domain.model.PromotionEntity
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun promoAdapterDelegate() = adapterDelegateViewBinding<PromotionEntity, DisplayableItem, ItemPromoBinding>(
    { layoutInflater, root -> ItemPromoBinding.inflate(layoutInflater, root, false) }
) {
    bind {
        val productAdapter = ProductAdapter()
        binding.recyclerApp.apply {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
            itemAnimator = DefaultItemAnimator()
        }
        productAdapter.setItems(item.promos)
        binding.tvTitle.text = item.title
    }
}
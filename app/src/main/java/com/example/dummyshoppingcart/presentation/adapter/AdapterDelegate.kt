package com.example.dummyshoppingcart.presentation.adapter

import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dummyshoppingcart.databinding.ItemCategoryBinding
import com.example.dummyshoppingcart.databinding.ItemPromoBinding
import com.example.dummyshoppingcart.domain.interfaces.OnProductClick
import com.example.dummyshoppingcart.domain.interfaces.DisplayableItem
import com.example.dummyshoppingcart.domain.interfaces.OnCategoryClick
import com.example.dummyshoppingcart.domain.model.CategoryListEntity
import com.example.dummyshoppingcart.domain.model.PromotionEntity
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun promoAdapterDelegate(onProductClick: OnProductClick) =
    adapterDelegateViewBinding<PromotionEntity, DisplayableItem, ItemPromoBinding>(
    { layoutInflater, root -> ItemPromoBinding.inflate(layoutInflater, root, false) }
) {
    bind {
        val productAdapter = ProductAdapter(onProductClick)
        binding.recyclerProduct.apply {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(
                itemView.context,
                RecyclerView.HORIZONTAL,
                false
            )
            itemAnimator = DefaultItemAnimator()
        }
        productAdapter.setItems(item.promos)
        binding.productTitle.text = item.title
    }
}

fun categoryAdapterDelegate(onCategoryClick: OnCategoryClick) =
    adapterDelegateViewBinding<CategoryListEntity, DisplayableItem, ItemCategoryBinding>(
    { layoutInflater, root -> ItemCategoryBinding.inflate(layoutInflater, root, false) }
) {
    bind {
        val categoryAdapter = CategoryAdapter(onCategoryClick)
        binding.recyclerCategory.apply {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(
                itemView.context,
                RecyclerView.HORIZONTAL,
                false
            )
            itemAnimator = DefaultItemAnimator()
        }
        categoryAdapter.setItems(item.categories)
        binding.categoryTitle.text = item.title
    }
}
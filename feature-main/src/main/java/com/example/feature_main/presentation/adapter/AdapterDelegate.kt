package com.example.dummyshoppingcart.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.interfaces.DisplayableItem
import com.example.core.domain.interfaces.OnCategoryClick
import com.example.core.domain.interfaces.OnProductClick
import com.example.dummyshoppingcart.domain.model.CategoryEntity
import com.example.dummyshoppingcart.domain.model.CategoryListEntity
import com.example.dummyshoppingcart.domain.model.ProductEntity
import com.example.dummyshoppingcart.domain.model.PromotionEntity
import com.example.feature_main.databinding.ItemCategoryBinding
import com.example.feature_main.databinding.ItemPromoBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun promoAdapterDelegate(onProductClick: OnProductClick<View, ProductEntity>) =
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

fun categoryAdapterDelegate(onCategoryClick: OnCategoryClick<View, CategoryEntity>) =
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
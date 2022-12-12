package com.example.dummyshoppingcart.presentation.adapter

import android.view.View
import com.example.data_products.domain.model.DisplayableItem
import com.example.core.domain.interfaces.OnCategoryClick
import com.example.core.domain.interfaces.OnProductClick
import com.example.data_products.domain.model.CategoryEntity
import com.example.data_products.domain.model.ProductEntity
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class MainAdapter(
    onProductClick: OnProductClick<View, ProductEntity>,
    onCategoryClick: OnCategoryClick<View, CategoryEntity>
) : ListDelegationAdapter<List<DisplayableItem>>(
    promoAdapterDelegate(onProductClick),
    categoryAdapterDelegate(onCategoryClick),
)



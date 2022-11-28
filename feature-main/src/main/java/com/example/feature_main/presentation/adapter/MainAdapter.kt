package com.example.dummyshoppingcart.presentation.adapter

import com.example.dummyshoppingcart.domain.interfaces.OnProductClick
import com.example.dummyshoppingcart.domain.interfaces.DisplayableItem
import com.example.dummyshoppingcart.domain.interfaces.OnCategoryClick
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class MainAdapter(
    onProductClick: OnProductClick,
    onCategoryClick: OnCategoryClick
) : ListDelegationAdapter<List<DisplayableItem>>(
    promoAdapterDelegate(onProductClick),
    categoryAdapterDelegate(onCategoryClick),
)



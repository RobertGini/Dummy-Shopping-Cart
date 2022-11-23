package com.example.dummyshoppingcart.presentation.adapter

import com.example.dummyshoppingcart.domain.iterators.DisplayableItem
import com.example.dummyshoppingcart.presentation.adapter.promoAdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class MainAdapter(onProductClick: () -> Unit) : ListDelegationAdapter<List<DisplayableItem>>(
    promoAdapterDelegate(onProductClick),
    categoryAdapterDelegate(),
)



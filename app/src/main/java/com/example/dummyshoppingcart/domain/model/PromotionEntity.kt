package com.example.dummyshoppingcart.domain.model

import com.example.dummyshoppingcart.domain.iterators.DisplayableItem

data class PromotionEntity(
    val title: String,
    val promos: List<ProductEntity>
): DisplayableItem

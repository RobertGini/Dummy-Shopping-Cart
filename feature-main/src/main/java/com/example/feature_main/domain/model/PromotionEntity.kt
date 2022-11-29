package com.example.dummyshoppingcart.domain.model

import com.example.core.domain.interfaces.DisplayableItem

data class PromotionEntity(
    val title: String,
    val promos: List<ProductEntity>
): DisplayableItem

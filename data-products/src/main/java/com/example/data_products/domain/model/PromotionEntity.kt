package com.example.data_products.domain.model

data class PromotionEntity(
    val title: String,
    val promos: List<ProductEntity>
): DisplayableItem

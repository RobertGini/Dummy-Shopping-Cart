package com.example.dummyshoppingcart.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductEntity(
    val product_id: Int = 0,
    val product_title: String = "",
    val product_category: String = "",
    val product_image: String = "",
    val product_description: String = "",
    val product_price: Double = 0.0,
    val product_ratings: ProductRatingEntity,
)

@Serializable
data class ProductRatingEntity(
    val product_rate: Double = 0.0,
    val product_count: Int = 0,
)

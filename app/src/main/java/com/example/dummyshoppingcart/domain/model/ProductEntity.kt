package com.example.dummyshoppingcart.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductEntity(
    val product_id: Int = 0,
    val product_title: String = "",
    val product_price: Int = 0,
    val product_description: String = "",
    val product_images: String = "",
)

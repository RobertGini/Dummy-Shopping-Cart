package com.example.data_details.data.api

data class Cart(
    var details_id: String? = null,
    val details_title: String? = null,
    val details_price: String? = null,
    val details_description: String? = null,
    val details_images: List<String>? = null,
)

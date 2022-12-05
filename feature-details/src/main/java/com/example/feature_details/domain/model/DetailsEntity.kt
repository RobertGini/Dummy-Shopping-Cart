package com.example.feature_details.domain.model

data class DetailsEntity(
    val details_id: String = "",
    val details_title: String = "",
    val details_price: String = "",
    val details_description: String = "",
    val details_images: List<String>,
)

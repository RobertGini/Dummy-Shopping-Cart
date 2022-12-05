package com.example.feature_details.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductDetails(
    @Json(name = "id")
    val details_id: Int?,

    @Json(name = "title")
    val details_title: String?,

    @Json(name = "price")
    val details_price: Long?,

    @Json(name = "description")
    val details_description: String?,

    @Json(name = "images")
    val details_images: List<String>?,
)

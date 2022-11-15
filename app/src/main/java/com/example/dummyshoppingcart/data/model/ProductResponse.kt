package com.example.dummyshoppingcart.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductResponse(

    @Json(name = "id")
    val product_id: Int?,

    @Json(name = "title")
    val product_title: String?,

    @Json(name = "category")
    val product_category: String?,

    @Json(name = "image")
    val product_image: String?,

    @Json(name = "description")
    val product_description: String?,

    @Json(name = "price")
    val product_price: Double?,

    @Json(name = "rating")
    val product_ratings: ProductRatingResponse,
)

@JsonClass(generateAdapter = true)
data class ProductRatingResponse(
    @Json(name = "rate")
    val product_rate: Double?,

    @Json(name = "count")
    val product_count: Int?,
)


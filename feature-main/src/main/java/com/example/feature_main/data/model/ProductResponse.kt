package com.example.feature_main.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductResponse(

    @Json(name = "id")
    val product_id: Int?,

    @Json(name = "title")
    val product_title: String?,

    @Json(name = "price")
    val product_price: Long?,

    @Json(name = "description")
    val product_description: String?,

    @Json(name = "images")
    val product_images: List<String>?,
)




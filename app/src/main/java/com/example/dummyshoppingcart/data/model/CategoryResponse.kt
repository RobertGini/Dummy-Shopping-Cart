package com.example.dummyshoppingcart.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoryResponse(
    @Json(name = "id")
    val category_id: Int?,

    @Json(name = "name")
    val category_name: String?,

    @Json(name = "image")
    val category_image: String?,
)

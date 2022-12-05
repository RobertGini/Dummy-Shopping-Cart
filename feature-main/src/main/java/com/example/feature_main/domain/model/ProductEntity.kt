package com.example.dummyshoppingcart.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductEntity(
    val product_id: String = "",
    val product_title: String = "",
    val product_price: String = "",
    val product_description: String = "",
    val product_images: List<String>,
): Parcelable

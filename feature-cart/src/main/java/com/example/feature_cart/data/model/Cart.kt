package com.example.feature_cart.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cart(
    var cart_id: String = "",
    val cart_title: String = "",
    val cart_price: String = "",
    val cart_description: String = "",
    val cart_images: List<String>,
) : Parcelable

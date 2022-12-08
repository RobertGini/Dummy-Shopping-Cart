package com.example.feature_cart.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cart(
    var details_id: String? = null,
    val details_title: String? = null,
    val details_price: String? = null,
    val details_description: String? = null,
    val details_images: List<String>? = null,
) : Parcelable

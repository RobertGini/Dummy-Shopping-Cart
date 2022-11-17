package com.example.dummyshoppingcart.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductEntity(
    val product_id: String = "",
    val product_title: String = "",
    val product_price: String = "",
    val product_description: String = "",
    val product_images: List<String>,
) : DelegateAdapterItem {
    override fun id(): Any = product_id

}

package com.example.dummyshoppingcart.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CategoryEntity(
    val category_id: String = "",
    val category_name: String = "",
    val category_image: String = "",
): DelegateAdapterItem {
    override fun id(): Any = category_id
}

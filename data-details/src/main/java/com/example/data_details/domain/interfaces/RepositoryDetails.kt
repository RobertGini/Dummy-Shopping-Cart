package com.example.data_details.domain.interfaces

import com.example.core.utils.Resource
import com.example.data_details.domain.model.DetailsEnitiy

interface RepositoryDetails {
    suspend fun getProductsDetails(productId: Int): DetailsEnitiy

    suspend fun getProductByCategory(productId: Int): List<DetailsEnitiy>

    suspend fun addCart(cart: DetailsEnitiy, result: (Resource<DetailsEnitiy>) -> Unit)

    suspend fun getCarts(
        result:(Resource<List<DetailsEnitiy>>) -> Unit,
        resultSum: (Resource<String>) -> Unit
    )
}
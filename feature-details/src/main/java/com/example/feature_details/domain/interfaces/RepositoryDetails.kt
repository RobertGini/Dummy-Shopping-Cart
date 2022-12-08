package com.example.feature_details.domain.interfaces

import com.example.core.utils.Resource
import com.example.feature_details.domain.model.DetailsEntity

interface RepositoryDetails {
    suspend fun getProductsDetails(productId: Int): DetailsEntity

    suspend fun getProductByCategory(productId: Int): List<DetailsEntity>

    suspend fun addCart(cart: DetailsEntity, result: (Resource<DetailsEntity>) -> Unit)
}
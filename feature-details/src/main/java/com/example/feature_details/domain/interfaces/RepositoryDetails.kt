package com.example.feature_details.domain.interfaces

import com.example.feature_details.domain.model.DetailsEntity

interface RepositoryDetails {
    suspend fun getProductsDetails(productId: Int): DetailsEntity
}
package com.example.feature_details.domain.useCase

interface UseCaseDetails<P> {
    suspend fun getProductsDetails(productId: Int): P

    suspend fun getProductByCategory(productId: Int) : List<P>
}
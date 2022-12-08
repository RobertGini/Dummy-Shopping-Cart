package com.example.feature_details.domain.useCase

import com.example.core.utils.Resource

interface UseCaseDetails<P> {
    suspend fun getProductsDetails(productId: Int): P

    suspend fun getProductByCategory(productId: Int) : List<P>

    suspend fun addCart(cart: P, result: (Resource<P>) -> Unit)
}
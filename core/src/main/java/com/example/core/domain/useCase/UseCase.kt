package com.example.dummyshoppingcart.domain.useCase

interface UseCase<P, C> {
    suspend fun getAllProducts() : List<P>

    suspend fun getAllCategories() : List<C>

    suspend fun getProductByCategory(productId: Int) : List<P>
}
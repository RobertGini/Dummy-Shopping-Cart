package com.example.dummyshoppingcart.domain.useCase

interface UseCase<Any> {
    suspend fun getAllProducts() : Any

    suspend fun getAllCategories() : Any

    suspend fun getProductByCategory(productId: Int) : Any
}
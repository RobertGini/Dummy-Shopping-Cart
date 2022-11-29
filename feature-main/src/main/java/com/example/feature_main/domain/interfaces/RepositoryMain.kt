package com.example.dummyshoppingcart.domain.interfaces

import com.example.dummyshoppingcart.domain.model.CategoryEntity
import com.example.dummyshoppingcart.domain.model.ProductEntity

interface RepositoryMain {
    suspend fun getAllProducts(): List<ProductEntity>

    suspend fun getAllCategories(): List<CategoryEntity>

    suspend fun getProductByCategory(productId: Int): List<ProductEntity>
}
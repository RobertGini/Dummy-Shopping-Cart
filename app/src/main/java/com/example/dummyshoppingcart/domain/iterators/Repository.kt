package com.example.dummyshoppingcart.domain.iterators

import com.example.dummyshoppingcart.domain.model.CategoryEntity
import com.example.dummyshoppingcart.domain.model.ProductEntity

interface Repository {
    suspend fun getAllProducts() : List<ProductEntity>

    suspend fun getAllCategories() : List<CategoryEntity>
}
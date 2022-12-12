package com.example.data_products.domain.interfaces

import com.example.data_products.domain.model.CategoryEntity
import com.example.data_products.domain.model.DisplayableItem
import com.example.data_products.domain.model.ProductEntity

interface RepositoryMain {
    suspend fun getAllProducts(): List<ProductEntity>

    suspend fun getAllCategories(): List<CategoryEntity>

    suspend fun getDisplayableItem(): List<DisplayableItem>
}
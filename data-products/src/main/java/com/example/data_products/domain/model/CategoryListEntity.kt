package com.example.data_products.domain.model

data class CategoryListEntity (
    val title: String,
    val categories: List<CategoryEntity>
) : DisplayableItem
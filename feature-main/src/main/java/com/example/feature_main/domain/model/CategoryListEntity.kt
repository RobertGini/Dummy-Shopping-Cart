package com.example.dummyshoppingcart.domain.model

import com.example.dummyshoppingcart.domain.interfaces.DisplayableItem

data class CategoryListEntity (
    val title: String,
    val categories: List<CategoryEntity>
) : DisplayableItem
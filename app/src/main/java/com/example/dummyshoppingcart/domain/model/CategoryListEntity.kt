package com.example.dummyshoppingcart.domain.model

import com.example.dummyshoppingcart.domain.iterators.DisplayableItem

data class CategoryListEntity (
    val title: String,
    val categories: List<CategoryEntity>
) : DisplayableItem
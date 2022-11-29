package com.example.core.domain.interfaces

interface OnCategoryClick<V, C> {
    fun onCategoryClick(view: V, categoryEntity: C)
}
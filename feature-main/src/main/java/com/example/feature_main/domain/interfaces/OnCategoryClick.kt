package com.example.dummyshoppingcart.domain.interfaces

import android.view.View
import com.example.dummyshoppingcart.domain.model.CategoryEntity

interface OnCategoryClick {
    fun onCategoryClick(view: View, categoryEntity: CategoryEntity)
}
package com.example.data_products.domain.mapper

import com.example.core.utils.ResourceProvider
import com.example.core.R
import com.example.data_products.domain.model.*
import javax.inject.Inject

class EntityToDisplayableItem @Inject constructor(
    private val resourceProvider: ResourceProvider
) {
    fun mapEntityToDisplayable(
        promotion: List<ProductEntity>,
        categoryList: List<CategoryEntity>
    ): List<DisplayableItem> {
        val listItems = ArrayList<DisplayableItem>()
        if (promotion.isNotEmpty()) {
            listItems.add(
                PromotionEntity(
                    resourceProvider.getString(R.string.available_products),
                    promotion
                )
            )
        }
        if (categoryList.isNotEmpty()) {
            listItems.add(
                CategoryListEntity(
                    resourceProvider.getString(R.string.available_categories),
                    categoryList
                )
            )
        }
        return listItems.toList()
    }
}
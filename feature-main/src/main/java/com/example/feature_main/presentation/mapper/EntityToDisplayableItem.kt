package com.example.feature_main.presentation.mapper

import com.example.core.domain.interfaces.DisplayableItem
import com.example.core.utils.ResourceProvider
import com.example.dummyshoppingcart.domain.model.CategoryEntity
import com.example.dummyshoppingcart.domain.model.CategoryListEntity
import com.example.dummyshoppingcart.domain.model.ProductEntity
import com.example.dummyshoppingcart.domain.model.PromotionEntity
import com.example.feature_main.R
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
package com.example.feature_main.data.mapper

import com.example.dummyshoppingcart.domain.model.CategoryEntity
import com.example.dummyshoppingcart.domain.model.ProductEntity
import com.example.feature_main.data.model.CategoryResponse
import com.example.feature_main.data.model.ProductResponse
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class ResponseDataMapper @Inject constructor() {

    @Singleton
    @Provides
    fun mappingProductResponse(response: List<ProductResponse>): List<ProductEntity> {
        return response.map { mapProduct(it) }
    }

    @Singleton
    @Provides
    fun mapProduct(response: ProductResponse): ProductEntity {
        return ProductEntity(
            product_id = response.product_id.toString(),
            product_title = response.product_title.toString(),
            product_price = response.product_price.toString(),
            product_description = response.product_description.toString(),
            product_images = response.product_images!!
        )
    }

    @Singleton
    @Provides
    fun mappingCategoryResponse(response: List<CategoryResponse>): List<CategoryEntity> {
        return response.map { mapCategory(it) }
    }

    @Singleton
    @Provides
    fun mapCategory(response: CategoryResponse): CategoryEntity {
        return CategoryEntity(
            category_id = response.category_id.toString(),
            category_image = response.category_image.toString(),
            category_name = response.category_name.toString()
        )
    }
}
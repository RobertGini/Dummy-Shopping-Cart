package com.example.dummyshoppingcart.data.mapper

import com.example.dummyshoppingcart.data.model.CategoryResponse
import com.example.dummyshoppingcart.data.model.ProductResponse
import com.example.dummyshoppingcart.domain.model.CategoryEntity
import com.example.dummyshoppingcart.domain.model.ProductEntity
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class ResponseDataMapper @Inject constructor() {

    @Singleton
    @Provides
    fun mappingProductResponse(response: List<ProductResponse>): ProductEntity {
        return response.map { mapProduct(response[0]) }[0]
    }

    @Singleton
    @Provides
    fun mapProduct(response: ProductResponse): ProductEntity {
        return ProductEntity(
            product_id = response.product_id!!,
            product_title = response.product_title.toString(),
            product_price = response.product_price!!,
            product_description = response.product_description.toString(),
            product_images = response.product_images?.map { it }.toString()
        )
    }

    @Singleton
    @Provides
    fun mappingCategoryResponse(response: List<CategoryResponse>): CategoryEntity {
        return response.map { mapCategory(response[0]) }[0]
    }

    @Singleton
    @Provides
    fun mapCategory(response: CategoryResponse): CategoryEntity {
        return CategoryEntity(
            category_id = response.category_id!!,
            category_image = response.category_image.toString(),
            category_name = response.category_name.toString()
        )
    }
}
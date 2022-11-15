package com.example.dummyshoppingcart.data.mapper

import com.example.dummyshoppingcart.data.model.ProductRatingResponse
import com.example.dummyshoppingcart.data.model.ProductResponse
import com.example.dummyshoppingcart.domain.model.ProductEntity
import com.example.dummyshoppingcart.domain.model.ProductRatingEntity
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class ResponseDataMapper @Inject constructor() {

    @Singleton
    @Provides
    fun mappingData(response: List<ProductResponse>): ProductEntity {
        return response.map { mapResponse(response[0], response[0].product_ratings) }[0]
    }

    @Singleton
    @Provides
    fun mapResponse(
        response: ProductResponse,
        responseRating: ProductRatingResponse
    ): ProductEntity {
        return ProductEntity(
            product_id = response.product_id!!,
            product_title = response.product_title.toString(),
            product_category = response.product_category.toString(),
            product_image = response.product_image.toString(),
            product_description = response.product_description.toString(),
            product_price = response.product_price!!,
            product_ratings = mapRatingResponse(responseRating)
        )
    }

    @Singleton
    @Provides
    fun mapRatingResponse(responseRating: ProductRatingResponse): ProductRatingEntity {
        return ProductRatingEntity(
            product_rate = responseRating.product_rate!!,
            product_count = responseRating.product_count!!,
        )
    }
}
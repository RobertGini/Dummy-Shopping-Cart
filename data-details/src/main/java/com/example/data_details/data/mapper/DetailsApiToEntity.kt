package com.example.data_details.data.mapper

import com.example.data_details.data.model.ProductDetails
import com.example.data_details.domain.model.DetailsEnitiy
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class DetailsApiToEntity @Inject constructor(){

    @Singleton
    @Provides
    fun mappingProductResponse(response: List<ProductDetails>): List<DetailsEnitiy> {
        return response.map { mapProduct(it) }
    }

    @Singleton
    @Provides
    fun mapProduct(response: ProductDetails): DetailsEnitiy {
        return DetailsEnitiy(
            details_id = response.details_id.toString(),
            details_title = response.details_title.toString(),
            details_price = response.details_price.toString() + "$",
            details_description = response.details_description.toString(),
            details_images = response.details_images!!
        )
    }
}
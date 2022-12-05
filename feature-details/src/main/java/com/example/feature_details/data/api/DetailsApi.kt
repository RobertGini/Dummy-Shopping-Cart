package com.example.feature_details.data.api

import com.example.feature_details.data.model.ProductDetails
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsApi {
    @GET("products/{id}")
    suspend fun getProductsDetails(
        @Path("id") id: Int
    ): ProductDetails
}
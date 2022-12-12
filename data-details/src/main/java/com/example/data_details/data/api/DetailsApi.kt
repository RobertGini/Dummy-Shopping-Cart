package com.example.data_details.data.api

import com.example.data_details.data.model.ProductDetails
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsApi {
    @GET("products/{id}")
    suspend fun getProductsDetails(
        @Path("id") id: Int
    ): ProductDetails

    @GET("categories/{id}/products")
    suspend fun getProductByCategory(
        @Path("id") id: Int
    ): List<ProductDetails>
}
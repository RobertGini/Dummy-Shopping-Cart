package com.example.dummyshoppingcart.data.api

import com.example.dummyshoppingcart.data.model.CategoryResponse
import com.example.dummyshoppingcart.data.model.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("products/")
    suspend fun getAllProducts(): List<ProductResponse>

    @GET("categories/")
    suspend fun getAllCategories(): List<CategoryResponse>

    @GET("categories/{id}/products")
    suspend fun getProductByCategory(
        @Path("id") id: Int
    ): List<ProductResponse>
}
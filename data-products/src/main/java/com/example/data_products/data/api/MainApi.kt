package com.example.data_products.data.api

import com.example.data_products.data.model.CategoryResponse
import com.example.data_products.data.model.ProductResponse
import retrofit2.http.GET

interface MainApi {
    @GET("products/")
    suspend fun getAllProducts(): List<ProductResponse>

    @GET("categories/")
    suspend fun getAllCategories(): List<CategoryResponse>
}
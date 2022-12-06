package com.example.feature_main.data.api

import com.example.feature_main.data.model.CategoryResponse
import com.example.feature_main.data.model.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MainApi {
    @GET("products/")
    suspend fun getAllProducts(): List<ProductResponse>

    @GET("categories/")
    suspend fun getAllCategories(): List<CategoryResponse>
}
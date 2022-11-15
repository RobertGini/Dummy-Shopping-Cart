package com.example.dummyshoppingcart.data.api

import com.example.dummyshoppingcart.data.model.ProductResponse
import retrofit2.http.GET

interface ApiService {
    @GET("products/")
    suspend fun getAllProducts(): List<ProductResponse>
}
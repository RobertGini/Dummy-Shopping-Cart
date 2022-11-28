package com.example.feature_main.data.repository

import com.example.dummyshoppingcart.domain.interfaces.Repository
import com.example.dummyshoppingcart.domain.model.ProductEntity
import com.example.feature_main.data.api.MainApi
import com.example.feature_main.data.mapper.ResponseDataMapper
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: MainApi,
    private val mapper: ResponseDataMapper
) : Repository {
    override suspend fun getAllProducts() =
        mapper.mappingProductResponse(apiService.getAllProducts())

    override suspend fun getAllCategories() =
        mapper.mappingCategoryResponse(apiService.getAllCategories())

    override suspend fun getProductByCategory(productId: Int): List<ProductEntity> =
        mapper.mappingProductResponse(apiService.getProductByCategory(productId))
}

package com.example.dummyshoppingcart.data.repositories

import com.example.dummyshoppingcart.data.api.ApiService
import com.example.dummyshoppingcart.data.mapper.ResponseDataMapper
import com.example.dummyshoppingcart.domain.interfaces.Repository
import com.example.dummyshoppingcart.domain.model.ProductEntity
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: ResponseDataMapper
) : Repository {
    override suspend fun getAllProducts() =
        mapper.mappingProductResponse(apiService.getAllProducts())

    override suspend fun getAllCategories() =
        mapper.mappingCategoryResponse(apiService.getAllCategories())

    override suspend fun getProductByCategory(productId: Int): List<ProductEntity> =
        mapper.mappingProductResponse(apiService.getProductByCategory(productId))

}

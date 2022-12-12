package com.example.data_products.data.repository

import com.example.data_products.data.api.MainApi
import com.example.data_products.data.mapper.ResponseDataMapper
import com.example.data_products.domain.interfaces.RepositoryMain
import com.example.data_products.domain.mapper.EntityToDisplayableItem
import com.example.data_products.domain.model.DisplayableItem
import javax.inject.Inject

class RepositoryMainImpl @Inject constructor(
    private val apiService: MainApi,
    private val mapper: ResponseDataMapper,
    private val mapperToDisplayableItem: EntityToDisplayableItem
) : RepositoryMain {
    override suspend fun getAllProducts() =
        mapper.mappingProductResponse(apiService.getAllProducts())

    override suspend fun getAllCategories() =
        mapper.mappingCategoryResponse(apiService.getAllCategories())

    override suspend fun getDisplayableItem(): List<DisplayableItem> =
        mapperToDisplayableItem.mapEntityToDisplayable(getAllProducts(), getAllCategories())
}

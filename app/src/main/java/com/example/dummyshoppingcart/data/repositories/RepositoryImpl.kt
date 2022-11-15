package com.example.dummyshoppingcart.data.repositories

import com.example.dummyshoppingcart.data.api.ApiService
import com.example.dummyshoppingcart.data.mapper.ResponseDataMapper
import com.example.dummyshoppingcart.domain.iterators.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: ResponseDataMapper
) : Repository {
    override suspend fun getAllProducts() =
        mapper.mappingData(apiService.getAllProducts())
}
package com.example.feature_details.data.repository

import com.example.feature_details.data.api.DetailsApi
import com.example.feature_details.data.mapper.DetailsApiToEntity
import com.example.feature_details.domain.interfaces.RepositoryDetails
import com.example.feature_details.domain.model.DetailsEntity
import javax.inject.Inject

class RepositoryDetailsImpl @Inject constructor(
    private val api: DetailsApi,
    private val mapper: DetailsApiToEntity
) : RepositoryDetails {
    override suspend fun getProductsDetails(productId: Int): DetailsEntity =
        mapper.mapProduct(api.getProductsDetails(productId))
}
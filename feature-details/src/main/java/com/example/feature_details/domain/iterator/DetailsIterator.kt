package com.example.feature_details.domain.iterator

import com.example.feature_details.domain.interfaces.RepositoryDetails
import com.example.feature_details.domain.model.DetailsEntity
import com.example.feature_details.domain.useCase.UseCaseDetails
import javax.inject.Inject

class DetailsIterator @Inject constructor(
    private val repositoryDetails: RepositoryDetails
): UseCaseDetails<DetailsEntity> {
    override suspend fun getProductsDetails(productId: Int): DetailsEntity {
        return repositoryDetails.getProductsDetails(productId)
    }
}
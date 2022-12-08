package com.example.feature_details.domain.iterator

import com.example.core.utils.Resource
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

    override suspend fun getProductByCategory(productId: Int): List<DetailsEntity> {
        return repositoryDetails.getProductByCategory(productId)
    }

    override suspend fun addCart(cart: DetailsEntity, result: (Resource<DetailsEntity>) -> Unit) {
        return repositoryDetails.addCart(cart) {}
    }
}
package com.example.feature_details.domain.iterator

import com.example.core.utils.Resource
import com.example.data_details.domain.interfaces.RepositoryDetails
import com.example.data_details.domain.model.DetailsEnitiy
import com.example.feature_details.domain.useCase.UseCaseDetails
import javax.inject.Inject

class DetailsIterator @Inject constructor(
    private val repositoryDetails: RepositoryDetails
): UseCaseDetails<DetailsEnitiy> {
    override suspend fun getProductsDetails(productId: Int): DetailsEnitiy {
        return repositoryDetails.getProductsDetails(productId)
    }

    override suspend fun getProductByCategory(productId: Int): List<DetailsEnitiy> {
        return repositoryDetails.getProductByCategory(productId)
    }

    override suspend fun addCart(cart: DetailsEnitiy, result: (Resource<DetailsEnitiy>) -> Unit) {
        return repositoryDetails.addCart(cart) {}
    }
}
package com.example.feature_details.data.repository

import com.example.core.utils.Resource
import com.example.feature_details.data.api.DetailsApi
import com.example.feature_details.data.mapper.DetailsApiToEntity
import com.example.feature_details.domain.interfaces.RepositoryDetails
import com.example.feature_details.domain.model.DetailsEntity
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class RepositoryDetailsImpl @Inject constructor(
    private val api: DetailsApi,
    private val mapper: DetailsApiToEntity,
    private val database: FirebaseDatabase
) : RepositoryDetails {

    override suspend fun getProductsDetails(productId: Int): DetailsEntity =
        mapper.mapProduct(api.getProductsDetails(productId))

    override suspend fun getProductByCategory(productId: Int): List<DetailsEntity> =
        mapper.mappingProductResponse(api.getProductByCategory(productId))

    //Add Product to Cart
    override suspend fun addCart(cart: DetailsEntity, result: (Resource<DetailsEntity>) -> Unit) {
        val reference = database.reference
            .child(FireDatabase.CART)
            .push()
        reference
            .setValue(cart)
        result.invoke(
            Resource.success(cart)
        )
    }

    object FireDatabase {
        var CART = "cart"
    }
}
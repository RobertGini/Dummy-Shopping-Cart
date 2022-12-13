package com.example.feature_details.data.repository

import com.example.core.utils.Resource
import com.example.data_details.data.api.Cart
import com.example.data_details.data.api.DetailsApi
import com.example.data_details.data.mapper.DetailsApiToEntity
import com.example.data_details.domain.interfaces.RepositoryDetails
import com.example.data_details.domain.model.DetailsEnitiy
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class RepositoryDetailsImpl @Inject constructor(
    private val api: DetailsApi,
    private val mapper: DetailsApiToEntity,
    private val database: FirebaseDatabase
) : RepositoryDetails {

    override suspend fun getProductsDetails(productId: Int): DetailsEnitiy =
        mapper.mapProduct(api.getProductsDetails(productId))

    override suspend fun getProductByCategory(productId: Int): List<DetailsEnitiy> =
        mapper.mappingProductResponse(api.getProductByCategory(productId))

    //Add Product to Cart
    override suspend fun addCart(cart: DetailsEnitiy, result: (Resource<DetailsEnitiy>) -> Unit) {
        val reference = database.reference
            .child(FireDatabase.CART)
            .push()
        reference
            .setValue(cart)
            .addOnSuccessListener {
                result.invoke(
                    Resource.success(cart)
                )
            }
            .addOnFailureListener {
                result.invoke(
                    Resource.error(data = null, it.localizedMessage as String)
                )
            }
    }

    override suspend fun getCarts(
        result:(Resource<List<DetailsEnitiy>>) -> Unit,
        resultSum: (Resource<String>) -> Unit
    ) {
        val reference = database.reference.child(FireDatabase.CART)
        reference.get()
            .addOnSuccessListener {
                val carts = ArrayList<Cart>()
                for (item in it.children) {
                    val cart = item.getValue(Cart::class.java)
                    carts.add(cart!!)
                }
                //Get Cart data from database
                result.invoke(
                    Resource.success(mapper.mappingDatabaseResponse(carts))
                )
                //Get sum of all cart items
                resultSum.invoke(
                    Resource.success(mapper.sumOfCarts(carts))
                )
            }
            .addOnFailureListener {
                result.invoke(
                    Resource.error(data = null, it.localizedMessage as String)
                )
            }
    }

    object FireDatabase {
        var CART = "cart"
    }
}
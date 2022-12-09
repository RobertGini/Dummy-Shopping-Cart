package com.example.feature_cart.data.repository

import com.example.core.utils.Resource
import com.example.feature_cart.data.model.Cart
import com.example.feature_cart.domain.repository.CartRepository
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val database: FirebaseDatabase
) : CartRepository {

    override suspend fun getCart(result: (Resource<List<Cart>>) -> Unit) {
        val reference = database.reference.child(FireDatabase.CART)
        reference.get()
            .addOnSuccessListener {
                val carts = ArrayList<Cart>()
                for (item in it.children) {
                    val cart = item.getValue(Cart::class.java)
                    carts.add(cart!!)
                }
                result.invoke(
                    Resource.success(carts)
                )
            }
            .addOnFailureListener{
                result.invoke(
                    Resource.error(data = null, it.localizedMessage as String)
                )
            }
    }

    object FireDatabase {
        var CART = "cart"
    }
}
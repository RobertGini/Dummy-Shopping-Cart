package com.example.feature_cart.data.repository

import com.example.feature_cart.data.model.Cart
import com.example.feature_cart.domain.repository.CartRepository
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val database: FirebaseDatabase
) : CartRepository {

    override fun addCart(cart: Cart) {
        val reference = database.reference.child(FireDatabase.CART).push()
        val uniqueKey = reference.key?: "invalid"
        cart.cart_id = uniqueKey
        reference
            .setValue(cart)
    }

    override fun getCart(cart: Cart) {
        val reference = database.reference
        reference.get()
            .addOnSuccessListener {
                val carts = arrayListOf<Cart>()
                for (item in it.children) {
                    val cart = item.getValue(Cart::class.java)
                    carts.add(cart!!)
                }
            }
    }

    object FireDatabase {
        var CART = "cart"
    }
}
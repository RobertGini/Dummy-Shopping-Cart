package com.example.feature_cart.domain.repository

import com.example.feature_cart.data.model.Cart

interface CartRepository {
    fun addCart(cart: Cart)
    fun getCart(cart: Cart)
}
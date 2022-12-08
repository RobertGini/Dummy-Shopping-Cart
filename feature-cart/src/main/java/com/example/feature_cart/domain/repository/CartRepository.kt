package com.example.feature_cart.domain.repository

import com.example.core.utils.Resource
import com.example.feature_cart.data.model.Cart

interface CartRepository {
    suspend fun getCart(result: (Resource<List<Cart>>) -> Unit)
}
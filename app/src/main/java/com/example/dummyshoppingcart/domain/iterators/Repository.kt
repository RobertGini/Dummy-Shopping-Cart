package com.example.dummyshoppingcart.domain.iterators

import com.example.dummyshoppingcart.domain.model.ProductEntity

interface Repository {
    suspend fun getAllProducts() : ProductEntity
}
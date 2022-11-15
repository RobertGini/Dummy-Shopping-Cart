package com.example.dummyshoppingcart.domain.useCase

interface UseCase<R> {
    suspend fun getAllProducts() : R
}
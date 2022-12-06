package com.example.feature_main.domain.interfaces

interface UseCase<P, C> {
    suspend fun getAllProducts() : List<P>

    suspend fun getAllCategories() : List<C>
}
package com.example.feature_catalogue.domain.interfaces

interface UseCase<P, C, D> {
    suspend fun getAllProducts() : List<P>

    suspend fun getAllCategories() : List<C>

    suspend fun getDisplayableItem() : List<D>
}
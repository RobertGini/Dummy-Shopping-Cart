package com.example.dummyshoppingcart.domain.iterators

import com.example.dummyshoppingcart.domain.interfaces.Repository
import com.example.dummyshoppingcart.domain.model.CategoryEntity
import com.example.dummyshoppingcart.domain.model.ProductEntity
import com.example.dummyshoppingcart.domain.useCase.UseCase
import javax.inject.Inject

class IteratorUseCase @Inject constructor(
    private val repository: Repository
) : UseCase<Any> {
    override suspend fun getAllProducts(): List<ProductEntity> {
        return repository.getAllProducts()
    }
    override suspend fun getAllCategories(): List<CategoryEntity> {
        return repository.getAllCategories()
    }
}
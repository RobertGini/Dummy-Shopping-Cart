package com.example.dummyshoppingcart.domain.iterators

import com.example.dummyshoppingcart.domain.model.ProductEntity
import com.example.dummyshoppingcart.domain.useCase.UseCase
import javax.inject.Inject

class IteratorUseCase @Inject constructor(
    private val repository: Repository
) : UseCase<ProductEntity> {
    override suspend fun getAllProducts(): ProductEntity {
        return repository.getAllProducts()
    }
}
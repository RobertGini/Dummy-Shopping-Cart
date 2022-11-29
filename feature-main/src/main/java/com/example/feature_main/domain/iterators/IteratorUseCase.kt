package com.example.dummyshoppingcart.domain.iterators

import com.example.dummyshoppingcart.domain.interfaces.RepositoryMain
import com.example.dummyshoppingcart.domain.model.CategoryEntity
import com.example.dummyshoppingcart.domain.model.ProductEntity
import com.example.dummyshoppingcart.domain.useCase.UseCase
import javax.inject.Inject

class IteratorUseCase @Inject constructor(
    private val repositoryMain: RepositoryMain
) : UseCase<ProductEntity, CategoryEntity> {
    override suspend fun getAllProducts(): List<ProductEntity> {
        return repositoryMain.getAllProducts()
    }
    override suspend fun getAllCategories(): List<CategoryEntity> {
        return repositoryMain.getAllCategories()
    }

    override suspend fun getProductByCategory(productId: Int): List<ProductEntity> {
        return repositoryMain.getProductByCategory(productId)
    }
}
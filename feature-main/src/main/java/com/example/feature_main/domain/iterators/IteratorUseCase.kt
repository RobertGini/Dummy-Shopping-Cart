package com.example.dummyshoppingcart.domain.iterators

import com.example.data_products.domain.interfaces.RepositoryMain
import com.example.data_products.domain.model.CategoryEntity
import com.example.data_products.domain.model.DisplayableItem
import com.example.data_products.domain.model.ProductEntity
import com.example.feature_main.domain.interfaces.UseCase
import javax.inject.Inject

class IteratorUseCase @Inject constructor(
    private val repositoryMain: RepositoryMain
) : UseCase<ProductEntity, CategoryEntity, DisplayableItem> {

    override suspend fun getAllProducts(): List<ProductEntity> {
        return repositoryMain.getAllProducts()
    }
    override suspend fun getAllCategories(): List<CategoryEntity> {
        return repositoryMain.getAllCategories()
    }

    override suspend fun getDisplayableItem(): List<DisplayableItem> {
        return repositoryMain.getDisplayableItem()
    }
}
package com.example.dummyshoppingcart.presentation.catalogueFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.dummyshoppingcart.domain.iterators.IteratorUseCase
import com.example.core.utils.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CatalogueViewModel @Inject constructor(
    val repository: IteratorUseCase
) : ViewModel() {

    fun getListOfCategories() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getAllCategories()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}
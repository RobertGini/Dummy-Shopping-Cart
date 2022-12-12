package com.example.feature_catalogue.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.core.utils.Resource
import com.example.feature_catalogue.domain.iterators.CatalogueIterator
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CatalogueViewModel @Inject constructor(
    private val iterator: CatalogueIterator
) : ViewModel() {

    fun getListOfCategories() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = iterator.getAllCategories()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}
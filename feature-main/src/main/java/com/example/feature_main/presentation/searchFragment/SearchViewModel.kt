package com.example.feature_main.presentation.searchFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.core.utils.Resource
import com.example.dummyshoppingcart.domain.iterators.IteratorUseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    val iterator: IteratorUseCase
) : ViewModel() {

    fun getProducts() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = iterator.getAllProducts()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}
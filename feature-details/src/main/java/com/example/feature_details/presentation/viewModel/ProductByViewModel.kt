package com.example.feature_details.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.core.utils.Resource
import com.example.feature_details.domain.iterator.DetailsIterator
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class ProductByViewModel @Inject constructor(
    val iterator: DetailsIterator
) : ViewModel() {

    fun getProductByCategory(productId: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = iterator.getProductByCategory(productId)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}
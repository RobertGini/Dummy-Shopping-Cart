package com.example.feature_details.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.core.utils.Resource
import com.example.feature_details.domain.iterator.DetailsIterator
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DescriptionViewModel @Inject constructor(
    private val iterator: DetailsIterator
) : ViewModel() {

    fun getProductsDetails(productId: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = iterator.getProductsDetails(productId)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}
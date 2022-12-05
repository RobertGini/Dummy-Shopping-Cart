package com.example.dummyshoppingcart.presentation.productByFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.dummyshoppingcart.domain.iterators.IteratorUseCase
import com.example.core.utils.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class ProductByViewModel @Inject constructor(
    val iterator: IteratorUseCase
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
package com.example.feature_details.presentation.viewModel

import androidx.lifecycle.*
import com.example.core.utils.Resource
import com.example.data_details.domain.model.DetailsEnitiy
import com.example.feature_details.domain.iterator.DetailsIterator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

    private val _addCart = MutableLiveData<Resource<DetailsEnitiy>>()
    val addCart: LiveData<Resource<DetailsEnitiy>>
        get() = _addCart

    fun addCart(cart: DetailsEnitiy) = viewModelScope.launch(Dispatchers.IO) {
        iterator.addCart(cart) { _addCart.value = it}
    }
}
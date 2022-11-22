package com.example.dummyshoppingcart.presentation.MainFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.dummyshoppingcart.domain.iterators.IteratorUseCase
import com.example.dummyshoppingcart.domain.model.DelegateAdapterItem
import com.example.dummyshoppingcart.utils.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: IteratorUseCase
) : ViewModel() {

    fun getListOfProducts() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getAllProducts()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    private val _listItems = MutableLiveData<List<DelegateAdapterItem>>()
    val listItems : LiveData<List<DelegateAdapterItem>>
        get() = _listItems

    fun getListOfCategories() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = listOf(repository.getAllCategories(), repository.getAllProducts())))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}
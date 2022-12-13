package com.example.feature_cart.presentation.viewModel

import androidx.lifecycle.*
import com.example.core.utils.Resource
import com.example.data_details.domain.interfaces.RepositoryDetails
import com.example.data_details.domain.model.Cart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartViewModel @Inject constructor(
    private val repository: RepositoryDetails
) : ViewModel() {

    private val _carts = MutableLiveData<Resource<List<Cart>>>()
    val carts: LiveData<Resource<List<Cart>>>
        get() = _carts

    fun getCart() = viewModelScope.launch(Dispatchers.IO) {
        _carts.postValue(Resource.loading(data = null))
        repository.getCart { _carts.value = it }
    }
}
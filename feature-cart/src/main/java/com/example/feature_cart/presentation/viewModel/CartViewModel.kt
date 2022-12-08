package com.example.feature_cart.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.Resource
import com.example.feature_cart.data.model.Cart
import com.example.feature_cart.domain.repository.CartRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartViewModel @Inject constructor(
    private val repository: CartRepository
) : ViewModel() {

    private val _carts = MutableLiveData<Resource<List<Cart>>>()
    val carts: LiveData<Resource<List<Cart>>>
        get() = _carts

    fun getCart() = viewModelScope.launch(Dispatchers.IO) {
        repository.getCart { _carts.value = it }
    }

}
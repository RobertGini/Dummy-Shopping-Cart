package com.example.feature_cart.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.feature_cart.data.model.Cart
import com.example.feature_cart.data.repository.CartRepositoryImpl

import javax.inject.Inject

class CartViewModel @Inject constructor(
    val repository: CartRepositoryImpl
) : ViewModel() {

    private val _addCart = MutableLiveData<List<Cart>>()
    val addCart: LiveData<List<Cart>>
        get() = _addCart

    private val _carts = MutableLiveData<List<Cart>>()
    val carts: LiveData<List<Cart>>
        get() = _carts

    fun addCart(cart: Cart) {
        //repository.addCart(cart) { _addCart.value = it}
    }

}
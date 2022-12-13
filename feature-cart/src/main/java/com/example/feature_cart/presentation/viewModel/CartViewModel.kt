package com.example.feature_cart.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.Resource
import com.example.core.utils.ResourceProvider
import com.example.data_details.domain.interfaces.RepositoryDetails
import com.example.data_details.domain.model.DetailsEnitiy
import com.example.feature_cart.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartViewModel @Inject constructor(
    private val repository: RepositoryDetails,
) : ViewModel() {

    private val _carts = MutableLiveData<Resource<List<DetailsEnitiy>>>()
    val carts: LiveData<Resource<List<DetailsEnitiy>>>
        get() = _carts

    fun getCart() = viewModelScope.launch(Dispatchers.IO) {
        _carts.postValue(Resource.loading(data = null))
        repository.getCart { _carts.value = it }
    }
}
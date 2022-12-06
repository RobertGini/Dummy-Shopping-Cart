package com.example.dummyshoppingcart.presentation.mainFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.domain.interfaces.DisplayableItem
import com.example.core.presentation.baseViewModel.BaseViewModel
import com.example.core.utils.Resource
import com.example.dummyshoppingcart.domain.iterators.IteratorUseCase
import com.example.feature_main.presentation.mapper.EntityToDisplayableItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val iterator: IteratorUseCase,
    private val mapper: EntityToDisplayableItem
) : BaseViewModel() {

    private val _listItems = MutableLiveData<Resource<List<DisplayableItem>>>()
    val listItems : LiveData<Resource<List<DisplayableItem>>>
        get() = _listItems

    fun setup() = viewModelScope.launch(Dispatchers.IO) {
        val products = iterator.getAllProducts()
        val categories = iterator.getAllCategories()
        requestWithLiveData(_listItems) { mapper.mapEntityToDisplayable(products, categories) }
    }
}
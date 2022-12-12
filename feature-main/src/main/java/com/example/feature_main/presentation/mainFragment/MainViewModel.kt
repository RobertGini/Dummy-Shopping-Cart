package com.example.dummyshoppingcart.presentation.mainFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data_products.domain.model.DisplayableItem
import com.example.core.presentation.baseViewModel.BaseViewModel
import com.example.core.utils.Resource
import com.example.dummyshoppingcart.domain.iterators.IteratorUseCase
import com.example.data_products.domain.mapper.EntityToDisplayableItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val iterator: IteratorUseCase,
) : BaseViewModel() {

    private val _listItems = MutableLiveData<Resource<List<DisplayableItem>>>()
    val listItems : LiveData<Resource<List<DisplayableItem>>>
        get() = _listItems

    fun setup() = viewModelScope.launch(Dispatchers.IO) {
        requestWithLiveData(_listItems) { iterator.getDisplayableItem() }
    }
}
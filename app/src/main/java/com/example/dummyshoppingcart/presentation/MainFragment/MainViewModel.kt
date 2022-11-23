package com.example.dummyshoppingcart.presentation.MainFragment

import androidx.lifecycle.*
import com.example.dummyshoppingcart.domain.interfaces.DisplayableItem
import com.example.dummyshoppingcart.domain.iterators.IteratorUseCase
import com.example.dummyshoppingcart.domain.model.CategoryListEntity
import com.example.dummyshoppingcart.domain.model.PromotionEntity
import com.example.dummyshoppingcart.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: IteratorUseCase
) : ViewModel() {

    private val _listItems = MutableLiveData<List<DisplayableItem>>()
    val listItems : LiveData<List<DisplayableItem>>
        get() = _listItems

    init {
        setupList()
    }

    private fun setupList() = viewModelScope.launch(Dispatchers.IO){
        val listItems = ArrayList<DisplayableItem>()
        val products = repository.getAllProducts()
        val categories = repository.getAllCategories()
        listItems.add(PromotionEntity("Available products", products))
        listItems.add(CategoryListEntity("List of all categories", categories))
        viewModelScope.launch (Dispatchers.Main ) {
            _listItems.postValue(listItems)
        }
    }

    fun getListOfProducts() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getAllProducts()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getListOfCategories() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = listOf(repository.getAllCategories())))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}
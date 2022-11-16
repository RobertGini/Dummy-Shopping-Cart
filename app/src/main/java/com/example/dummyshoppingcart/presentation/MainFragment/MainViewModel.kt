package com.example.dummyshoppingcart.presentation.MainFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.dummyshoppingcart.domain.iterators.IteratorUseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: IteratorUseCase
) : ViewModel() {
    fun getDataOfProducts() = liveData(Dispatchers.IO) {
        emit(repository.getAllProducts())
    }

    fun getDataOfCategory() = liveData(Dispatchers.IO) {
        emit(repository.getAllCategories())
    }
}
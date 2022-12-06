package com.example.core.presentation.baseViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    fun <T> requestWithLiveData(
        liveData: MutableLiveData<Resource<T>>,
        response: suspend () -> T
    ) {
        this.viewModelScope.launch(Dispatchers.IO) {
            liveData.postValue(Resource.loading(data = null))
            try {
                val data = response.invoke()
                liveData.postValue(Resource.success(data))
            } catch (e: Exception) {
                liveData.postValue(
                    Resource.error(
                        data = null,
                        message = e.message ?: "Error Occurred!"
                    )
                )
            }
        }
    }
}
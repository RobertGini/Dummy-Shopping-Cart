package com.example.dummyshoppingcart.presentation.cartFragment

import androidx.lifecycle.ViewModel
import com.example.feature_catalogue.domain.iterators.IteratorUseCase
import javax.inject.Inject

class CartViewModel @Inject constructor(
    val repository: IteratorUseCase
) : ViewModel() {
}
package com.example.dummyshoppingcart.presentation.cartFragment

import androidx.lifecycle.ViewModel
import com.example.dummyshoppingcart.domain.iterators.IteratorUseCase
import javax.inject.Inject

class CartViewModel @Inject constructor(
    val repository: IteratorUseCase
) : ViewModel() {
}
package com.example.dummyshoppingcart.presentation.CatalogueFragment

import androidx.lifecycle.ViewModel
import com.example.dummyshoppingcart.domain.iterators.IteratorUseCase
import javax.inject.Inject

class CatalogueViewModel @Inject constructor(
    val repository: IteratorUseCase
) : ViewModel() {
}
package com.example.dummyshoppingcart.presentation.DescriptionFragment

import androidx.lifecycle.ViewModel
import com.example.dummyshoppingcart.domain.iterators.IteratorUseCase
import javax.inject.Inject

class DescriptionViewModel  @Inject constructor(
    val repository: IteratorUseCase
) : ViewModel() {
}
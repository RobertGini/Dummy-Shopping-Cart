package com.example.dummyshoppingcart.presentation.descriptionFragment

import androidx.lifecycle.ViewModel
import com.example.dummyshoppingcart.domain.iterators.IteratorUseCase
import javax.inject.Inject

class DescriptionViewModel  @Inject constructor(
    val repository: IteratorUseCase
) : ViewModel() {
}
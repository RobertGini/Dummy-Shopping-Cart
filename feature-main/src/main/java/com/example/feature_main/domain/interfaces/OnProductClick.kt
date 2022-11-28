package com.example.dummyshoppingcart.domain.interfaces

import android.view.View
import com.example.dummyshoppingcart.domain.model.ProductEntity

interface OnProductClick {
    fun onProductClicked(view: View, productEntity: ProductEntity )
}
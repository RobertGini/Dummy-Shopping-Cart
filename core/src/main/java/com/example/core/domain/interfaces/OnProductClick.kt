package com.example.core.domain.interfaces

interface OnProductClick<V, P> {
    fun onProductClicked(view: V, productEntity: P )
}
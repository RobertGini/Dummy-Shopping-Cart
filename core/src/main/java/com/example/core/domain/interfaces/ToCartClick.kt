package com.example.core.domain.interfaces

interface ToCartClick<V, P> {
    fun toCartClicked(view: V, entity: P )
}
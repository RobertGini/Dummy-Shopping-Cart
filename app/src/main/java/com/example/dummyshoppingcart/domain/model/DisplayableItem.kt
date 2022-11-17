package com.example.dummyshoppingcart.domain.model

interface DelegateAdapterItem {

    fun id(): Any

    fun payload(other: Any): Payloadable = Payloadable.None

    interface Payloadable {
        object None: Payloadable
    }
}
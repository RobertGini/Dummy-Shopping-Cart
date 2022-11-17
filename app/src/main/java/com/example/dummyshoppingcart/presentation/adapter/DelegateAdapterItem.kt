package com.example.dummyshoppingcart.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dummyshoppingcart.domain.model.DelegateAdapterItem

abstract class DelegateAdapter<M, in VH : RecyclerView.ViewHolder>(val modelClass: Class<out M>) {

    abstract fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    abstract fun bindViewHolder(model: M, viewHolder: VH, payloads: List<DelegateAdapterItem.Payloadable>)
}
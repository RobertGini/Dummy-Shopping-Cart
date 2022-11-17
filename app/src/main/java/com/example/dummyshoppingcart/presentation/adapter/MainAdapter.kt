package com.example.dummyshoppingcart.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.example.dummyshoppingcart.R
import com.example.dummyshoppingcart.databinding.ItemListBinding
import com.example.dummyshoppingcart.domain.model.DelegateAdapterItem
import com.example.dummyshoppingcart.domain.model.ProductEntity

class MainAdapter :
    DelegateAdapter<ProductEntity, MainAdapter.MainViewHolder>(ProductEntity::class.java) {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder  {
        val binding =
            ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun bindViewHolder(
        model: ProductEntity,
        viewHolder: MainViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(model)
    }

    inner class MainViewHolder(
        private val binding: ItemListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entity: ProductEntity) = with(binding) {
            rcTitle.text = entity.product_title
            rcPrice.text = entity.product_price
            rcImage.load(
                entity.product_images[0]
            ){
                crossfade(true)
                placeholder(R.mipmap.ic_launcher)
                transformations(CircleCropTransformation())
            }
        }
    }
}



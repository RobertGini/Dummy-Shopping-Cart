package com.example.dummyshoppingcart.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.example.dummyshoppingcart.R
import com.example.dummyshoppingcart.databinding.ItemListBinding
import com.example.dummyshoppingcart.domain.model.CategoryEntity
import com.example.dummyshoppingcart.domain.model.DelegateAdapterItem

class CategoryAdapter :
    DelegateAdapter<CategoryEntity, CategoryAdapter.CategoryViewHolder>(CategoryEntity::class.java) {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder  {
        val binding =
            ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun bindViewHolder(
        model: CategoryEntity,
        viewHolder: CategoryViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>
    ) {
        viewHolder.bind(model)
    }

    inner class CategoryViewHolder(
        private val binding: ItemListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entity: CategoryEntity) = with(binding) {
            rcTitle.text = entity.category_name
            rcImage.load(
                entity.category_image
            ){
                crossfade(true)
                placeholder(R.mipmap.ic_launcher)
                transformations(CircleCropTransformation())
            }
        }
    }
}
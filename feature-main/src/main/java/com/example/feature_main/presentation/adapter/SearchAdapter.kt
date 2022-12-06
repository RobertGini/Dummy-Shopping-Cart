package com.example.feature_main.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.example.core.databinding.ItemListBinding
import com.example.core.domain.interfaces.OnProductClick
import com.example.dummyshoppingcart.domain.model.ProductEntity
import java.util.*

class SearchAdapter(
    private val onProductClick: OnProductClick<View, ProductEntity>
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>(), Filterable {

    //Getting Items from Api
    private var items = ArrayList<ProductEntity>()
    //FilteredList
    private var filteredItems = ArrayList<ProductEntity>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchAdapter.SearchViewHolder {
        val binding = ItemListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SearchViewHolder(binding)
    }

    override fun getItemCount() = filteredItems.size


    override fun onBindViewHolder(holder: SearchAdapter.SearchViewHolder, position: Int) {
        holder.bind(filteredItems[position])
    }

    inner class SearchViewHolder(
        private val binding: ItemListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entity: ProductEntity) {
            binding.apply {
                rcTitle.text = entity.product_title
                rcPrice.text = entity.product_price
                rcImage.load(
                    entity.product_images[0]
                ) {
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }
                itemContainer.setOnClickListener {
                    onProductClick.onProductClicked(it, entity)
                }
            }
        }
    }

    fun setItems(data: List<ProductEntity>) {
        items.addAll(data)
        filteredItems = items
        notifyDataSetChanged()
    }

    //Perform filtering of list
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    filteredItems = items
                } else {
                    val resultList = ArrayList<ProductEntity>()
                    for (row in items) {
                        if (row.product_title.lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    filteredItems = resultList

                    Log.d("performFiltering: t1: ", resultList.size.toString())
                }
                return FilterResults().apply { values = filteredItems }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredItems = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<ProductEntity>
                notifyDataSetChanged()

                Log.d("performFiltering", "called " + filteredItems.size)

            }
        }
    }
}
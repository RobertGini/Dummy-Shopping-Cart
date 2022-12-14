package com.example.feature_catalogue.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.domain.interfaces.OnCategoryClick
import com.example.core.utils.Status
import com.example.core.utils.gone
import com.example.core.utils.show
import com.example.data_products.domain.model.CategoryEntity
import com.example.feature_catalogue.R
import com.example.feature_catalogue.databinding.FragmentCatalogueBinding
import com.example.feature_catalogue.presentation.adapter.CategoryAdapter
import com.example.feature_catalogue.presentation.viewModel.CatalogueViewModel
import com.example.navigation.DeepLinkDestination
import com.example.navigation.deepLinkNavigateTo
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CatalogueFragment :
    DaggerFragment(R.layout.fragment_catalogue),
    OnCategoryClick<View, CategoryEntity>
{
    private var _binding: FragmentCatalogueBinding? = null
    private val binding get() = _binding!!

    private var categoryListener: OnCategoryClick<View, CategoryEntity> = this

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val catalogueViewModel: CatalogueViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatalogueBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        catalogueViewModel.getListOfCategories().observe(viewLifecycleOwner) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { data ->
                            showData(data = data)
                        }
                    }
                    Status.ERROR -> {
                    }
                    Status.LOADING -> { showLoading() }
                }
            }
        }
    }

    private fun showData(data: List<CategoryEntity>){
        binding.statusLayout.root.gone()
        binding.rcCatalogue.show()
        setupAdapter(data)
    }

    private fun showLoading() {
        binding.statusLayout.apply {
            root.show()
            loading.show()
        }
    }

    private fun setupAdapter(data: List<CategoryEntity>) {
        val categoryAdapter = CategoryAdapter(categoryListener)
        binding.rcCatalogue.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 2)
            adapter = categoryAdapter
            itemAnimator = DefaultItemAnimator()
        }
        categoryAdapter.setItems(data)
        categoryAdapter.notifyDataSetChanged()
    }

    override fun onCategoryClick(view: View, categoryEntity: CategoryEntity) {
        val productId = categoryEntity.category_id.toInt()
        findNavController().deepLinkNavigateTo(DeepLinkDestination.ProductBy(productId))
        Log.d(TAG, "Clicked on category")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = CatalogueFragment()
        const val TAG = "CatalogueFragment"

    }
}
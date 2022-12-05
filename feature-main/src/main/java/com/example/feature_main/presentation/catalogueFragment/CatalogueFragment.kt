package com.example.feature_main.presentation.catalogueFragment

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
import com.example.dummyshoppingcart.domain.model.CategoryEntity
import com.example.dummyshoppingcart.presentation.adapter.CategoryAdapter
import com.example.feature_main.R
import com.example.feature_main.databinding.FragmentCatalogueBinding
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
                        val data = resource.data!!
                        setupAdapter(data)
                    }
                    Status.ERROR -> {
                    }
                    Status.LOADING -> {
                    }
                }
            }
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
    }

    override fun onCategoryClick(view: View, categoryEntity: CategoryEntity) {
        val productId = categoryEntity.category_id.toInt()
//        val action =
//            CatalogueFragmentDirections.actionNavigationCatalogueToProductByFragment(productId)
//        findNavController().navigate(action)
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
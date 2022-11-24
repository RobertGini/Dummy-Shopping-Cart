package com.example.dummyshoppingcart.presentation.mainFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dummyshoppingcart.R
import com.example.dummyshoppingcart.databinding.FragmentMainBinding
import com.example.dummyshoppingcart.domain.interfaces.OnCategoryClick
import com.example.dummyshoppingcart.domain.interfaces.OnProductClick
import com.example.dummyshoppingcart.domain.model.CategoryEntity
import com.example.dummyshoppingcart.domain.model.ProductEntity
import com.example.dummyshoppingcart.presentation.adapter.MainAdapter
import com.example.dummyshoppingcart.presentation.catalogueFragment.CatalogueFragment
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MainFragment:
    DaggerFragment(R.layout.fragment_main),
    OnProductClick,
    OnCategoryClick
{
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private var productListener: OnProductClick = this
    private var categoryListener: OnCategoryClick = this
    private val mainAdapter by lazy {
        MainAdapter (
            productListener,
            categoryListener
        )
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val mainViewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupProduct()
    }

    private fun setupAdapter() {
        binding.rcMain.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                RecyclerView.VERTICAL,
                false
            )
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun setupProduct() {
        mainViewModel.listItems.observe(viewLifecycleOwner) {
            it.let {
                mainAdapter.items = it
                mainAdapter.notifyDataSetChanged()
                Log.d(TAG, "${mainAdapter.items}")
            }
        }
    }

    override fun onProductClicked(view: View, productEntity: ProductEntity) {
        val action =
            MainFragmentDirections.actionNavigationMainToDescriptionFragment(productEntity)
        findNavController().navigate(action)
        Log.d(TAG, "Clicked on Product")
    }

    override fun onCategoryClick(view: View, categoryEntity: CategoryEntity) {
        val productId = categoryEntity.category_id.toInt()
        val action =
            MainFragmentDirections.actionNavigationMainToProductByFragment(productId)
        findNavController().navigate(action)
        Log.d(CatalogueFragment.TAG, "Clicked on category")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "MainFragment"
        fun newInstance() = MainFragment()
    }
}
package com.example.feature_main.presentation.searchFragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.domain.interfaces.OnProductClick
import com.example.core.utils.Status
import com.example.data_products.domain.model.ProductEntity
import com.example.feature_main.R
import com.example.feature_main.databinding.FragmentSearchBinding
import com.example.feature_main.presentation.adapter.SearchAdapter
import com.example.navigation.DeepLinkDestination
import com.example.navigation.deepLinkNavigateTo
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SearchFragment :
    DaggerFragment(R.layout.fragment_search),
    OnProductClick<View, ProductEntity> {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private var productListener: OnProductClick<View, ProductEntity> = this
    private val searchAdapter by lazy {
        SearchAdapter( productListener )
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val searchViewModel: SearchViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbarInFragment()
        setupObservers()
    }

    private fun setupObservers() {
        searchViewModel.getProducts().observe(viewLifecycleOwner) {
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

    private fun setupAdapter(data: List<ProductEntity>) {
        binding.rcSearch.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 2)
            adapter = searchAdapter
            itemAnimator = DefaultItemAnimator()
        }
        searchAdapter.setItems(data)
        searchAdapter.notifyDataSetChanged()
    }

    private fun setupToolbarInFragment() {
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.overflow_menu, menu)

                val searchItem = menu.findItem(R.id.toolbar_search)
                val searchView = searchItem?.actionView as SearchView
                searchView.queryHint = "Search for a product..."

                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        if (query != null) {
                            searchAdapter.filter.filter(query)
                            Log.d(TAG, "Submit Search")
                        }
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        searchAdapter.filter.filter(newText)
                        Log.d(TAG, "Input words")
                        return false
                    }
                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.toolbar_search -> {
                        Log.d(TAG, "Clicked on ItemMenu")
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    //On click sending ID of Item to DetailsFragment to set UI
    override fun onProductClicked(view: View, productEntity: ProductEntity) {
        val productId = productEntity.product_id.toInt()
        findNavController().deepLinkNavigateTo(DeepLinkDestination.Details(productId))
        Log.d(TAG, "Clicked on Product")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "SearchFragment"
    }
}
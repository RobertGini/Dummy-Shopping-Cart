package com.example.feature_main.presentation.mainFragment

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.interfaces.OnCategoryClick
import com.example.core.domain.interfaces.OnProductClick
import com.example.dummyshoppingcart.domain.model.CategoryEntity
import com.example.dummyshoppingcart.domain.model.ProductEntity
import com.example.dummyshoppingcart.presentation.adapter.MainAdapter
import com.example.dummyshoppingcart.presentation.mainFragment.MainViewModel
import com.example.feature_main.R
import com.example.feature_main.databinding.FragmentMainBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MainFragment:
    DaggerFragment(R.layout.fragment_main),
    OnProductClick<View, ProductEntity>,
    OnCategoryClick<View, CategoryEntity>
{
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private var productListener: OnProductClick<View, ProductEntity> = this
    private var categoryListener: OnCategoryClick<View, CategoryEntity> = this
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
        setupToolbarInFragment()
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
                        if (query != null){
                            Log.d(TAG, "Submit Search")
                        }
                        return false
                    }
                    override fun onQueryTextChange(newText: String?): Boolean {
                        return false
                    }
                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.toolbar_search -> {
                        //Navigate to Search Fragment
                        Log.d(TAG, "Clicked on ItemMenu")
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
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
        Log.d(TAG, "Clicked on category")
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
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
import com.example.core.utils.Status
import com.example.core.utils.gone
import com.example.core.utils.show
import com.example.data_products.domain.model.CategoryEntity
import com.example.data_products.domain.model.DisplayableItem
import com.example.data_products.domain.model.ProductEntity
import com.example.dummyshoppingcart.presentation.adapter.MainAdapter
import com.example.dummyshoppingcart.presentation.mainFragment.MainViewModel
import com.example.feature_main.R
import com.example.feature_main.databinding.FragmentMainBinding
import com.example.navigation.DeepLinkDestination
import com.example.navigation.deepLinkNavigateTo
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MainFragment :
    DaggerFragment(R.layout.fragment_main),
    OnProductClick<View, ProductEntity>,
    OnCategoryClick<View, CategoryEntity> {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private var productListener: OnProductClick<View, ProductEntity> = this
    private var categoryListener: OnCategoryClick<View, CategoryEntity> = this
    private val mainAdapter by lazy {
        MainAdapter(
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
        setupProduct()
        setupAdapter()
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
        mainViewModel.setup()
        mainViewModel.listItems.observe(viewLifecycleOwner) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { data ->
                            showData(data = data)
                        }
                        Log.d(TAG, "${mainAdapter.items}")
                    }
                    Status.ERROR -> {
                    }
                    Status.LOADING -> { showLoading() }
                }
            }
        }
    }

    private fun showData(data: List<DisplayableItem>){
        binding.statusLayout.root.gone()
        binding.rcMain.show()
        mainAdapter.items = data
        mainAdapter.notifyDataSetChanged()
    }

    private fun showLoading() {
        binding.rcMain.gone()
        binding.statusLayout.apply {
            root.show()
            loading.show()
        }
    }

    //Creating a specific toolbar for MainFragment
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
                        val action = MainFragmentDirections.actionMainFragmentToSearchFragment()
                        findNavController().navigate(action)
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
        const val TAG = "MainFragment"
        fun newInstance() = MainFragment()
    }
}
package com.example.feature_details.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.domain.interfaces.OnProductClick
import com.example.core.domain.interfaces.ToCartClick
import com.example.core.utils.Status
import com.example.core.utils.gone
import com.example.core.utils.show
import com.example.data_details.domain.model.DetailsEnitiy
import com.example.feature_details.R
import com.example.feature_details.databinding.FragmentProductByBinding
import com.example.feature_details.presentation.adapter.ProductByAdapter
import com.example.feature_details.presentation.viewModel.ProductByViewModel
import com.example.navigation.DeepLinkDestination
import com.example.navigation.deepLinkNavigateTo
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ProductByFragment :
    DaggerFragment(R.layout.fragment_product_by),
    OnProductClick<View, DetailsEnitiy>,
    ToCartClick<View, DetailsEnitiy> {

    private var _binding: FragmentProductByBinding? = null
    private val binding get() = _binding!!

    private var productListener: OnProductClick<View, DetailsEnitiy> = this
    private var cartListener: ToCartClick<View, DetailsEnitiy> = this

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val productByViewModel: ProductByViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductByBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getFromIdCatalogue()
    }

    private fun getFromIdCatalogue() {
        val args: ProductByFragmentArgs by navArgs()
        val item = args.categoryId
        setupObservers(item)
    }

    private fun setupObservers(productId: Int) {
        productByViewModel.getProductByCategory(productId).observe(viewLifecycleOwner) {
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

    private fun showData(data: List<DetailsEnitiy>){
        binding.statusLayout.root.gone()
        binding.rcProductBy.show()
        setupAdapter(data)
    }

    private fun showLoading() {
        binding.statusLayout.apply {
            root.show()
            loading.show()
        }
    }

    private fun setupAdapter(data: List<DetailsEnitiy>) {
        val productAdapter = ProductByAdapter(productListener, cartListener)
        binding.rcProductBy.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 2)
            adapter = productAdapter
            itemAnimator = DefaultItemAnimator()
        }
        productAdapter.setItems(data)
        productAdapter.notifyDataSetChanged()
    }

    override fun onProductClicked(view: View, productEntity: DetailsEnitiy) {
        val productId = productEntity.details_id.toInt()
        findNavController().deepLinkNavigateTo(DeepLinkDestination.Details(productId))
        Log.d(TAG, "Clicked")
    }

    //Add Item to Cart
    override fun toCartClicked(view: View, entity: DetailsEnitiy) {
        productByViewModel.addCart(entity)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "ProductByFragment"
        fun newInstance() = ProductByFragment()
    }
}
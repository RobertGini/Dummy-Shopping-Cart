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
import com.example.feature_details.R
import com.example.feature_details.databinding.FragmentProductByBinding
import com.example.feature_details.domain.model.DetailsEntity
import com.example.feature_details.presentation.adapter.ProductByAdapter
import com.example.feature_details.presentation.viewModel.ProductByViewModel
import com.example.navigation.DeepLinkDestination
import com.example.navigation.deepLinkNavigateTo
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ProductByFragment :
    DaggerFragment(R.layout.fragment_product_by),
    OnProductClick<View, DetailsEntity>,
    ToCartClick<View, DetailsEntity> {

    private var _binding: FragmentProductByBinding? = null
    private val binding get() = _binding!!

    private var productListener: OnProductClick<View, DetailsEntity> = this
    private var cartListener: ToCartClick<View, DetailsEntity> = this

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

    private fun setupAdapter(data: List<DetailsEntity>) {
        val productAdapter = ProductByAdapter(productListener, cartListener)
        binding.rcProductBy.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 2)
            adapter = productAdapter
            itemAnimator = DefaultItemAnimator()
        }
        productAdapter.setItems(data)
    }

    override fun onProductClicked(view: View, productEntity: DetailsEntity) {
        val productId = productEntity.details_id.toInt()
        findNavController().deepLinkNavigateTo(DeepLinkDestination.Details(productId))
        Log.d(TAG, "Clicked")
    }

    //Add Item to Cart
    override fun toCartClicked(view: View, entity: DetailsEntity) {
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
package com.example.feature_cart.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.core.domain.interfaces.OnProductClick
import com.example.core.utils.Status
import com.example.feature_cart.R
import com.example.feature_cart.data.model.Cart
import com.example.feature_cart.databinding.FragmentCartBinding
import com.example.feature_cart.presentation.adapter.CartAdapter
import com.example.feature_cart.presentation.viewModel.CartViewModel
import com.example.navigation.DeepLinkDestination
import com.example.navigation.deepLinkNavigateTo
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CartFragment :
    DaggerFragment(R.layout.fragment_cart),
    OnProductClick<View, Cart> {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private var productListener: OnProductClick<View, Cart> = this
    private val cartAdapter  by lazy {
        CartAdapter(productListener)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val cartViewModel: CartViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        getData()
    }

    private fun getData() {
        cartViewModel.getCart()
        cartViewModel.carts.observe(viewLifecycleOwner) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        cartAdapter.setItems(it.data!!)
                        Log.d(TAG, "${it.data}")
                    }
                    Status.ERROR -> {
                    }
                    Status.LOADING -> {
                    }
                }
            }
        }
    }

    private fun setupAdapter() {
        binding.rcCart.apply {
            adapter =  cartAdapter
            itemAnimator = DefaultItemAnimator()
        }
    }

    override fun onProductClicked(view: View, productEntity: Cart) {
        val productId = productEntity.details_id!!.toInt()
        findNavController().deepLinkNavigateTo(DeepLinkDestination.Details(productId))
        Log.d(TAG, "Clicked on Product")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "CartFragment"
        fun newInstance() = CartFragment()
    }


}
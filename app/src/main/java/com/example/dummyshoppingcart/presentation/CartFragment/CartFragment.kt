package com.example.dummyshoppingcart.presentation.CartFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.dummyshoppingcart.R
import com.example.dummyshoppingcart.databinding.FragmentCartBinding
import com.example.dummyshoppingcart.databinding.FragmentCatalogueBinding
import com.example.dummyshoppingcart.presentation.CatalogueFragment.CatalogueViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CartFragment : DaggerFragment(R.layout.fragment_cart) {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val cartViewModel: CatalogueViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = CartFragment()
    }
}
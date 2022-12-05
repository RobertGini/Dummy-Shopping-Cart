package com.example.feature_cart.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.feature_cart.R
import com.example.feature_cart.databinding.FragmentCartBinding
import com.example.feature_cart.presentation.adapter.CartAdapter
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.android.support.DaggerFragment

class CartFragment : DaggerFragment(R.layout.fragment_cart) {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

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

        val database = Firebase.database(getString(R.string.database_location))
        val myRef = database.getReference("message")
        myRef.setValue("hello")

    }

    private fun setupAdapter() {
        val cartAdapter = CartAdapter()
        binding.rcCart.apply {
            adapter =  cartAdapter
            itemAnimator = DefaultItemAnimator()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = CartFragment()
    }
}
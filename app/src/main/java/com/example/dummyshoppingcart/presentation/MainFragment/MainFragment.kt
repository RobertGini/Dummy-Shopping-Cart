package com.example.dummyshoppingcart.presentation.MainFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.dummyshoppingcart.R
import com.example.dummyshoppingcart.databinding.FragmentMainBinding
import com.example.dummyshoppingcart.domain.model.ProductEntity
import com.example.dummyshoppingcart.presentation.adapter.CategoryAdapter
import com.example.dummyshoppingcart.presentation.adapter.CompositeAdapter
import com.example.dummyshoppingcart.presentation.adapter.MainAdapter
import com.example.dummyshoppingcart.utils.Status
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MainFragment : DaggerFragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val compositeAdapter by lazy {
        CompositeAdapter.Builder()
            .add(CategoryAdapter())
            .add(MainAdapter { })
            .build()
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

    private fun setupProduct() {
        mainViewModel.getListOfProducts().observe(viewLifecycleOwner) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        val data = resource.data!!
                        Log.d(TAG, "Got Product data from API")
                        compositeAdapter.submitList(data)
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
        binding.rcMain.apply {
            adapter = compositeAdapter
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun onItemClick(data: ProductEntity) {
        // bundle...
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
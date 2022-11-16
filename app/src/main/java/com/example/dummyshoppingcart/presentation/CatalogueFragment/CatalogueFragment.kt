package com.example.dummyshoppingcart.presentation.CatalogueFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.dummyshoppingcart.R
import com.example.dummyshoppingcart.databinding.FragmentCatalogueBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CatalogueFragment : DaggerFragment(R.layout.fragment_catalogue) {

    private var _binding: FragmentCatalogueBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val catalogueViewModel: CatalogueViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCatalogueBinding.inflate(layoutInflater)
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
        fun newInstance() = CatalogueFragment()
    }
}
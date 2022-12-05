package com.example.feature_details.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import coil.api.load
import coil.transform.CircleCropTransformation
import com.example.core.utils.Status
import com.example.feature_details.R
import com.example.feature_details.databinding.FragmentDescriptionBinding
import com.example.feature_details.domain.model.DetailsEntity
import com.example.feature_details.presentation.viewModel.DescriptionViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class DescriptionFragment : DaggerFragment(R.layout.fragment_description) {

    private var _binding: FragmentDescriptionBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val descriptionViewModel: DescriptionViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDescriptionBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataFromClick()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupObservers(productId: Int){
        descriptionViewModel.getProductsDetails(productId).observe(viewLifecycleOwner) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        val data = resource.data!!
                        setUI(data)
                    }
                    Status.ERROR -> {
                    }
                    Status.LOADING -> {
                    }
                }
            }
        }
    }

    private fun getDataFromClick() {
        val args: DescriptionFragmentArgs by navArgs()
        val item = args.productDetailsId
        setupObservers(item)
    }

    private fun setUI(entity: DetailsEntity) {
        binding.apply {
            descTitle.text = entity.details_title
            descPrice.text = entity.details_price
            descDescription.text = entity.details_description
            descImage.load(
                entity.details_images[0]
            ){
                crossfade(true)
                //placeholder(R.mipmap.ic_launcher)
                transformations(CircleCropTransformation())
            }
        }
    }

    companion object {
        fun newInstance() = DescriptionFragment()
    }
}
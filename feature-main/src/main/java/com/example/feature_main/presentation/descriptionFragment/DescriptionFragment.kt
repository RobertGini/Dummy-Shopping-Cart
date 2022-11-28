package com.example.dummyshoppingcart.presentation.descriptionFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import coil.api.load
import coil.transform.CircleCropTransformation
import com.example.dummyshoppingcart.domain.model.ProductEntity
import com.example.feature_main.R
import com.example.feature_main.databinding.FragmentDescriptionBinding
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

    private fun getDataFromClick() {
        val args: DescriptionFragmentArgs by navArgs()
        val item = args.productEntity
        setUI(item)
    }

    private fun setUI(entity: ProductEntity) {
        binding.apply {
            descTitle.text = entity.product_title
            descPrice.text = entity.product_price
            descDescription.text = entity.product_description
            descImage.load(
                entity.product_images[0]
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
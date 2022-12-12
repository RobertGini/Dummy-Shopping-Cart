package com.example.dummyshoppingcart.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.di.ViewModelFactory
import com.example.dummyshoppingcart.di.scopes.AppScoped
import com.example.feature_catalogue.presentation.viewModel.CatalogueViewModel
import com.example.dummyshoppingcart.presentation.mainFragment.MainViewModel
import com.example.feature_cart.presentation.viewModel.CartViewModel
import com.example.feature_details.presentation.viewModel.ProductByViewModel
import com.example.feature_details.presentation.viewModel.DescriptionViewModel
import com.example.feature_main.presentation.searchFragment.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CatalogueViewModel::class)
    abstract fun bindCatalogueViewModel(viewModel: CatalogueViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductByViewModel::class)
    abstract fun bindProductByViewModel(viewModel: ProductByViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DescriptionViewModel::class)
    abstract fun bindDescriptionViewModel(viewModel: DescriptionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel::class)
    abstract fun bindCartViewModel(viewModel: CartViewModel): ViewModel

    @Binds
    @AppScoped
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
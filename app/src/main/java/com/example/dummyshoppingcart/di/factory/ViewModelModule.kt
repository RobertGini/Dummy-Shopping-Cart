package com.example.dummyshoppingcart.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dummyshoppingcart.presentation.cartFragment.CartViewModel
import com.example.dummyshoppingcart.presentation.catalogueFragment.CatalogueViewModel
import com.example.dummyshoppingcart.presentation.descriptionFragment.DescriptionViewModel
import com.example.dummyshoppingcart.presentation.mainFragment.MainViewModel
import com.example.dummyshoppingcart.presentation.productByFragment.ProductByViewModel
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
    @ViewModelKey(CartViewModel::class)
    abstract fun bindCartViewModel(viewModel: CartViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DescriptionViewModel::class)
    abstract fun bindDescriptionViewModel(viewModel: DescriptionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductByViewModel::class)
    abstract fun bindProductByViewModelViewModel(viewModel: ProductByViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
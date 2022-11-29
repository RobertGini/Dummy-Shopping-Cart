package com.example.dummyshoppingcart.di.modules

import com.example.dummyshoppingcart.di.scopes.FragmentScoped
import com.example.dummyshoppingcart.presentation.cartFragment.CartFragment
import com.example.dummyshoppingcart.presentation.catalogueFragment.CatalogueFragment
import com.example.dummyshoppingcart.presentation.descriptionFragment.DescriptionFragment
import com.example.dummyshoppingcart.presentation.productByFragment.ProductByFragment
import com.example.feature_main.presentation.mainFragment.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun mainFragment(): MainFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun catalogueFragment(): CatalogueFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun cartFragment(): CartFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun descriptionFragment(): DescriptionFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun productByFragment(): ProductByFragment
}
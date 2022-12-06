package com.example.dummyshoppingcart.di.modules

import com.example.dummyshoppingcart.di.scopes.FragmentScoped
import com.example.feature_cart.presentation.fragment.CartFragment
import com.example.feature_main.presentation.catalogueFragment.CatalogueFragment
import com.example.feature_details.presentation.fragment.DescriptionFragment
import com.example.feature_details.presentation.fragment.ProductByFragment
import com.example.feature_main.presentation.mainFragment.MainFragment
import com.example.feature_main.presentation.searchFragment.SearchFragment
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

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun searchFragment(): SearchFragment
}
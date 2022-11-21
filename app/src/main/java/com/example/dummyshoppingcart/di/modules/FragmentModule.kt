package com.example.dummyshoppingcart.di.modules

import com.example.dummyshoppingcart.di.scopes.FragmentScoped
import com.example.dummyshoppingcart.presentation.CartFragment.CartFragment
import com.example.dummyshoppingcart.presentation.CatalogueFragment.CatalogueFragment
import com.example.dummyshoppingcart.presentation.MainFragment.MainFragment
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
}
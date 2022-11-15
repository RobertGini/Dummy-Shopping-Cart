package com.example.dummyshoppingcart.di.modules

import com.example.dummyshoppingcart.di.scopes.ActivityScoped
import com.example.dummyshoppingcart.presentation.base.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            FragmentModule::class
        ]
    )
    abstract fun providesMainActivity(): MainActivity
}
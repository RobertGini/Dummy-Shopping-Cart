package com.example.dummyshoppingcart.di.modules

import android.app.Application
import android.content.Context
import com.example.dummyshoppingcart.di.factory.ViewModelModule
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        ViewModelModule::class,
        DataModule::class,
        ResourceProviderModule::class
    ]
)
abstract class AppModule {
    @Binds
    abstract fun bindContext(application: Application): Context
}
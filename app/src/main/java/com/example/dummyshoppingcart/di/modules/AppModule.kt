package com.example.dummyshoppingcart.di.modules

import com.example.dummyshoppingcart.di.factory.ViewModelModule
import dagger.Module

@Module(
    includes = [
        ViewModelModule::class,
        DataModule::class
    ]
)
abstract class AppModule
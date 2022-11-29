package com.example.dummyshoppingcart.di.modules

import com.example.dummyshoppingcart.domain.interfaces.RepositoryMain
import com.example.feature_main.data.repository.RepositoryMainImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


@Module(
    includes = [
        NetworkModule::class,
    ]
)
abstract class DataModule {
    @Singleton
    @Binds
    abstract fun provideRepository(repositoryMain: RepositoryMainImpl): RepositoryMain
}
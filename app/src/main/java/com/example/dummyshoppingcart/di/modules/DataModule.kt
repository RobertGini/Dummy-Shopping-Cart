package com.example.dummyshoppingcart.di.modules

import com.example.dummyshoppingcart.domain.interfaces.Repository
import com.example.feature_main.data.repository.RepositoryImpl
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
    abstract fun provideRepository(repositoryImpl: RepositoryImpl): Repository
}
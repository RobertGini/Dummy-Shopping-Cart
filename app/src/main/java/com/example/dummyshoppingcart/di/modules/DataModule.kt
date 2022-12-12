package com.example.dummyshoppingcart.di.modules

import com.example.data_details.domain.interfaces.RepositoryDetails
import com.example.data_products.data.repository.RepositoryMainImpl
import com.example.data_products.domain.interfaces.RepositoryMain
import com.example.feature_details.data.repository.RepositoryDetailsImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


@Module(
    includes = [
        NetworkModule::class,
        FirebaseModule::class
    ]
)
abstract class DataModule {
    @Singleton
    @Binds
    abstract fun provideMainRepository(repositoryMain: RepositoryMainImpl): RepositoryMain

    @Singleton
    @Binds
    abstract fun provideDetailsRepository(repositoryDetails: RepositoryDetailsImpl): RepositoryDetails
}
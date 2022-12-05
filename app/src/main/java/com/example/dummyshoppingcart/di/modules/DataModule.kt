package com.example.dummyshoppingcart.di.modules

import com.example.dummyshoppingcart.domain.interfaces.RepositoryMain
import com.example.feature_cart.data.repository.CartRepositoryImpl
import com.example.feature_cart.domain.repository.CartRepository
import com.example.feature_details.data.repository.RepositoryDetailsImpl
import com.example.feature_details.domain.interfaces.RepositoryDetails
import com.example.feature_main.data.repository.RepositoryMainImpl
import com.google.firebase.database.FirebaseDatabase
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
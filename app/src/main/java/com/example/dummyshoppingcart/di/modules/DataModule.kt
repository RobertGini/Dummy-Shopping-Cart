package com.example.dummyshoppingcart.di.modules

import com.example.dummyshoppingcart.data.mapper.ResponseDataMapper
import com.example.dummyshoppingcart.data.repositories.RepositoryImpl
import com.example.dummyshoppingcart.domain.interfaces.Repository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


@Module(
    includes = [
        NetworkModule::class,
        ResponseDataMapper::class,
    ]
)
abstract class DataModule {
    @Singleton
    @Binds
    abstract fun provideRepository(repositoryImpl: RepositoryImpl): Repository
}
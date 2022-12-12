package com.example.dummyshoppingcart.di.modules

import com.example.feature_details.data.api.DetailsApi
import com.example.data_products.data.api.MainApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    private val baseUrl = "https://api.escuelajs.co/api/v1/"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideMainApi(retrofit: Retrofit): MainApi = retrofit.create(MainApi::class.java)

    @Singleton
    @Provides
    fun provideDetailsApi(retrofit: Retrofit): DetailsApi = retrofit.create(DetailsApi::class.java)
}
package com.example.dummyshoppingcart.di.modules

import android.content.Context
import com.example.core.utils.ResourceProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ResourceProviderModule {
    @Provides
    @Singleton
    fun provideResourceProvider(context: Context): ResourceProvider = ResourceProvider(context)
}
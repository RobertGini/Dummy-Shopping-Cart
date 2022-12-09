package com.example.dummyshoppingcart.di.modules

import com.example.navigation.Navigator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavigationModule {
    @Provides
    @Singleton
    fun provideNavigation(): Navigator = Navigator()
}
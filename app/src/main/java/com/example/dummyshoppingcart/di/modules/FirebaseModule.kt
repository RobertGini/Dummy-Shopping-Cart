package com.example.dummyshoppingcart.di.modules

import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FirebaseModule {

    @Provides
    @Singleton
    fun provideFireBaseDatabaseInstance(): FirebaseDatabase = FirebaseDatabase.getInstance()

}
package com.example.dummyshoppingcart.di

import android.app.Application
import com.example.dummyshoppingcart.ApplicationClass
import com.example.dummyshoppingcart.di.modules.ActivityBindingModule
import com.example.dummyshoppingcart.di.modules.AppModule
import com.example.dummyshoppingcart.di.scopes.AppScoped
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@AppScoped
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBindingModule::class,
        AppModule::class
    ]
)
interface AppComponent : AndroidInjector<ApplicationClass> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(applicationClass: ApplicationClass)
}
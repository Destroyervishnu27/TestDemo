package com.testapp.di

import androidx.lifecycle.ViewModelProvider
import com.testapp.di.factory.ViewModelProviderFactory

import dagger.Binds
import dagger.Module

@Module
public abstract class ViewModelFactoryModule {

    // so it will provide us the dependency that we needed for view model
    // its similar to @Provider, so bindViewModelProvidersFactory() will return ViewModelProvidersFactory istanse
    @Binds
    public abstract fun bindViewModelProvidersFactory(factoryModule: ViewModelProviderFactory): ViewModelProvider.Factory
}
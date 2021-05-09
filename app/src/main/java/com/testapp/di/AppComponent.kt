package com.testapp.di

import android.app.Application
import com.testapp.TestApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RetrofitModule::class, AndroidSupportInjectionModule::class, ActivityBuilderModules::class
        , AppModule::class, ViewModelFactoryModule::class, ViewModelModule::class,
        FragmentModule::class]
)
interface AppComponent : AndroidInjector<TestApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    override fun inject(app: TestApp)

}
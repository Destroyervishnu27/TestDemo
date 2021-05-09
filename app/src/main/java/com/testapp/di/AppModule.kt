package com.testapp.di

import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/* here we will keep all app level dependencies such as retrofit instance, glide instance.
* anything that will exitst in complete app and not going to change in entrre life time of application
*
* */
@Module
object AppModule {

    @Singleton
    @Provides
    @JvmStatic
    fun getAppTag(): String {
        return "TestApp"
    }


}
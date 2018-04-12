package com.taidang.themoviedb.presentation.di.module

import android.app.Application
import android.content.Context
import com.taidang.themoviedb.BuildConfig
import com.taidang.themoviedb.domain.SchedulerFactory
import com.taidang.themoviedb.presentation.di.ApplicationScoped
import com.taidang.themoviedb.presentation.di.DependencyName
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @ApplicationScoped
    @Named(DependencyName.DI_NAME_DEBUGGABLE)
    fun providesDebuggable() = BuildConfig.DEBUG

    @Provides
    @ApplicationScoped
    @Named(DependencyName.DI_NAME_APP_CONTEXT)
    fun providesApplicationContext(): Context = application.applicationContext

    @Provides
    @ApplicationScoped
    @Named(DependencyName.DI_NAME_API_BASE_URL)
    fun providesApiBaseUrl() = "https://api.themoviedb.org/3/"

    @Provides
    @ApplicationScoped
    @Named(DependencyName.DI_NAME_API_KEY)
    fun providesApiKey() = BuildConfig.API_KEY

    @Provides
    @ApplicationScoped
    fun providesSchedulerFactory(): SchedulerFactory = SchedulerFactory()
}
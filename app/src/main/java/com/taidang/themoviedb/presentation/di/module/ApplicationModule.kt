package com.taidang.themoviedb.presentation.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.taidang.themoviedb.BuildConfig
import com.taidang.themoviedb.domain.SchedulerFactory
import com.taidang.themoviedb.presentation.di.ApplicationScoped
import com.taidang.themoviedb.presentation.di.DependencyName
import com.taidang.themoviedb.presentation.manager.AppConfigManager
import com.taidang.themoviedb.presentation.manager.SharedPrefAppConfigManager
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @ApplicationScoped
    @Named(DependencyName.DI_NAME_DEBUGGABLE)
    fun providesDebuggable(): Boolean = BuildConfig.DEBUG

    @Provides
    @ApplicationScoped
    @Named(DependencyName.DI_NAME_APP_CONTEXT)
    fun providesApplicationContext(): Context = application.applicationContext

    @Provides
    @ApplicationScoped
    @Named(DependencyName.DI_NAME_API_BASE_URL)
    fun providesApiBaseUrl(): String = "https://api.themoviedb.org/3/"

    @Provides
    @ApplicationScoped
    @Named(DependencyName.DI_NAME_API_KEY)
    fun providesApiKey(): String = BuildConfig.API_KEY

    @Provides
    @ApplicationScoped
    fun providesSchedulerFactory(): SchedulerFactory = SchedulerFactory()

    @Provides
    @ApplicationScoped
    fun providesSharedPreferences(@Named(DependencyName.DI_NAME_APP_CONTEXT) context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Provides
    @ApplicationScoped
    fun providesAppConfigManager(pref: SharedPreferences, gson: Gson): AppConfigManager {
        return SharedPrefAppConfigManager(pref, gson)
    }

    @Provides
    fun providesImagesConfig(appConfigManager: AppConfigManager) = appConfigManager.getImagesConfig()

}
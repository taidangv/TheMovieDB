package com.taidang.themoviedb.presentation

import android.app.Application
import com.taidang.themoviedb.di.module.NetworkModule
import com.taidang.themoviedb.di.module.RepositoryModule
import com.taidang.themoviedb.presentation.di.component.ApplicationComponent
import com.taidang.themoviedb.presentation.di.component.DaggerApplicationComponent
import com.taidang.themoviedb.presentation.di.module.ApplicationModule
import com.taidang.themoviedb.presentation.di.module.MapperModule

class TMDBApp : Application() {

    private lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .repositoryModule(RepositoryModule())
                .mapperModule(MapperModule())
                .networkModule(NetworkModule())
                .build()
    }

    fun getAppComponent() = appComponent
}
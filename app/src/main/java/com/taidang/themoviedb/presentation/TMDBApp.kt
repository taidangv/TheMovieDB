package com.taidang.themoviedb.presentation

import android.app.Application
import com.taidang.themoviedb.presentation.di.component.ApplicationComponent
import com.taidang.themoviedb.presentation.di.component.DaggerApplicationComponent
import com.taidang.themoviedb.presentation.di.module.ApplicationModule

class TMDBApp : Application() {

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}
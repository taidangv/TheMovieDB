package com.taidang.themoviedb.presentation.di.component

import com.taidang.themoviedb.presentation.activity.SplashActivity
import com.taidang.themoviedb.presentation.di.ActivityScoped
import com.taidang.themoviedb.presentation.di.module.SplashModule
import dagger.Subcomponent

@Subcomponent(modules = [SplashModule::class])
@ActivityScoped
interface SplashComponent {
    fun inject(splashActivity: SplashActivity)
}
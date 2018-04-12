package com.taidang.themoviedb.presentation.di.component

import com.taidang.themoviedb.presentation.activity.SplashActivity
import com.taidang.themoviedb.presentation.di.ActivityScoped
import com.taidang.themoviedb.presentation.di.module.SplashModule
import com.taidang.themoviedb.presentation.di.module.UsecaseModule
import dagger.Subcomponent

@Subcomponent(modules = [
    SplashModule::class,
    UsecaseModule::class
])
@ActivityScoped
interface SplashComponent {
    fun inject(splashActivity: SplashActivity)
}
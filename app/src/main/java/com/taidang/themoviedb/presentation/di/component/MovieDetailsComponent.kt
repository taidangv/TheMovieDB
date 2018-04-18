package com.taidang.themoviedb.presentation.di.component

import com.taidang.themoviedb.presentation.activity.MovieDetailsActivity
import com.taidang.themoviedb.presentation.di.ActivityScoped
import com.taidang.themoviedb.presentation.di.module.MovieDetailsModule
import dagger.Subcomponent

@Subcomponent(modules = [MovieDetailsModule::class])
@ActivityScoped
interface MovieDetailsComponent {
    fun inject(movieDetailsActivity: MovieDetailsActivity)
}
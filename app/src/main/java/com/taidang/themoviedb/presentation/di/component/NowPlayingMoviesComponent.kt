package com.taidang.themoviedb.presentation.di.component

import com.taidang.themoviedb.presentation.di.FragmentScoped
import com.taidang.themoviedb.presentation.di.module.NowPlayingMoviesModule
import com.taidang.themoviedb.presentation.fragment.NowPlayingMoviesFragment
import dagger.Subcomponent

@Subcomponent(modules = [NowPlayingMoviesModule::class])
@FragmentScoped
interface NowPlayingMoviesComponent {
    fun inject(fragment: NowPlayingMoviesFragment)
}
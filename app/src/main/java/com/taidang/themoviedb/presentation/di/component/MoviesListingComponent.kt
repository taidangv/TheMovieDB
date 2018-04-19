package com.taidang.themoviedb.presentation.di.component

import com.taidang.themoviedb.presentation.di.FragmentScoped
import com.taidang.themoviedb.presentation.di.module.MoviesListingModule
import com.taidang.themoviedb.presentation.fragment.NowPlayingMoviesFragment
import com.taidang.themoviedb.presentation.fragment.UpcomingMoviesFragment
import dagger.Subcomponent

@Subcomponent(modules = [MoviesListingModule::class])
@FragmentScoped
interface MoviesListingComponent {
    fun inject(fragment: NowPlayingMoviesFragment)
    fun inject(fragment: UpcomingMoviesFragment)
}
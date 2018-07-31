package com.taidang.themoviedb.presentation.di.component

import com.taidang.themoviedb.di.module.NetworkModule
import com.taidang.themoviedb.di.module.RepositoryModule
import com.taidang.themoviedb.presentation.TMDBApp
import com.taidang.themoviedb.presentation.activity.SplashActivity
import com.taidang.themoviedb.presentation.di.ApplicationScoped
import com.taidang.themoviedb.presentation.di.module.*
import com.taidang.themoviedb.presentation.fragment.NowPlayingMoviesFragment
import com.taidang.themoviedb.presentation.fragment.UpcomingMoviesFragment
import dagger.Component

@Component(modules = [
    ApplicationModule::class,
    RepositoryModule::class,
    NetworkModule::class,
    MapperModule::class,
    UsecaseModule::class,
    SplashModule::class,
    MoviesListingModule::class])
@ApplicationScoped
interface ApplicationComponent {
    fun inject(application: TMDBApp)
    fun inject(splashActivity: SplashActivity)
    fun inject(fragment: NowPlayingMoviesFragment)
    fun inject(fragment: UpcomingMoviesFragment)

    fun plus(movieDetailsModule: MovieDetailsModule): MovieDetailsComponent
}
package com.taidang.themoviedb.presentation.di.module

import com.taidang.themoviedb.presentation.contract.NowPlayingMoviesContract
import com.taidang.themoviedb.presentation.contract.UpcomingMoviesContract
import com.taidang.themoviedb.presentation.presenter.NowPlayingMoviesPresenter
import com.taidang.themoviedb.presentation.presenter.UpcomingMoviesPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class MoviesListingModule {

    @Binds
    abstract fun providesNowPlayingMoviesPresenter(p: NowPlayingMoviesPresenter): NowPlayingMoviesContract.Presenter

    @Binds
    abstract fun providesUpcomingMoviesPresenter(p: UpcomingMoviesPresenter): UpcomingMoviesContract.Presenter

}
package com.taidang.themoviedb.presentation.di.module

import com.taidang.themoviedb.domain.usecase.GetMoviesUsecase
import com.taidang.themoviedb.presentation.contract.NowPlayingMoviesContract
import com.taidang.themoviedb.presentation.contract.UpcomingMoviesContract
import com.taidang.themoviedb.presentation.manager.AppConfigManager
import com.taidang.themoviedb.presentation.presenter.NowPlayingMoviesPresenter
import com.taidang.themoviedb.presentation.presenter.UpcomingMoviesPresenter
import dagger.Module
import dagger.Provides

@Module
class MoviesListingModule {

    @Provides
    fun providesNowPlayingMoviesPresenter(getMoviesUsecase: GetMoviesUsecase, appConfigManager: AppConfigManager): NowPlayingMoviesContract.Presenter {
        return NowPlayingMoviesPresenter(getMoviesUsecase, appConfigManager)
    }

    @Provides
    fun providesUpcomingMoviesPresenter(getMoviesUsecase: GetMoviesUsecase, appConfigManager: AppConfigManager): UpcomingMoviesContract.Presenter {
        return UpcomingMoviesPresenter(getMoviesUsecase, appConfigManager)
    }

}
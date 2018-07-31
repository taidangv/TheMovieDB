package com.taidang.themoviedb.presentation.di.module

import com.taidang.themoviedb.domain.usecase.GetMovieDetailsUsecase
import com.taidang.themoviedb.presentation.contract.MovieDetailsContract
import com.taidang.themoviedb.presentation.di.DependencyName
import com.taidang.themoviedb.presentation.presenter.MovieDetailsPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class MovieDetailsModule(private val movieId: Int) {

    @Provides
    @Named(DependencyName.DI_MOVIE_ID)
    fun providesMovieId() = movieId


    @Module
    companion object {
        @JvmStatic
        @Provides
        fun providesMovieDetailsPresenter(getMovieDetailsUsecase: GetMovieDetailsUsecase, @Named(DependencyName.DI_MOVIE_ID) movieId: Int): MovieDetailsContract.Presenter {
            return MovieDetailsPresenter(getMovieDetailsUsecase, movieId)
        }
    }
}
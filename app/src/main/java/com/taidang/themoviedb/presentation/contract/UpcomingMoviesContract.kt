package com.taidang.themoviedb.presentation.contract

import com.taidang.themoviedb.domain.model.Movie
import com.taidang.themoviedb.presentation.contract.base.IBasePresenter
import com.taidang.themoviedb.presentation.contract.base.IBaseView

interface UpcomingMoviesContract {

    interface View : IBaseView<Presenter> {
        fun displayMovies(movies: List<Movie>)
        fun gotoDetailsMovie(movie: Movie)
    }

    interface Presenter : IBasePresenter<View> {
        fun chooseMovie(movie: Movie)
    }
}
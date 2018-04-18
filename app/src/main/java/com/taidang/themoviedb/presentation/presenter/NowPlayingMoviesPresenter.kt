package com.taidang.themoviedb.presentation.presenter

import com.taidang.themoviedb.domain.model.Movie
import com.taidang.themoviedb.domain.usecase.GetMoviesUsecase
import com.taidang.themoviedb.presentation.contract.NowPlayingMoviesContract
import io.reactivex.disposables.CompositeDisposable

class NowPlayingMoviesPresenter(private val getMoviesUsecase: GetMoviesUsecase, private val countryCode: String)
    : NowPlayingMoviesContract.Presenter {

    override var mView: NowPlayingMoviesContract.View? = null
    private val mDisposables = CompositeDisposable()

    override fun start() {
        getMoviesUsecase.getNowPlayingMovies(countryCode)
                .doOnSubscribe { mView?.displayLoading() }
                .subscribe(
                        { mView?.displayMovies(it.movies) },
                        { mView?.displayError(it) })
    }

    override fun chooseMovie(movie: Movie) {
        mView?.gotoDetailsMovie(movie)
    }

    override fun attachView(v: NowPlayingMoviesContract.View) {
        mView = v
    }

    override fun destroy() {
        mView = null
        mDisposables.clear()
    }
}
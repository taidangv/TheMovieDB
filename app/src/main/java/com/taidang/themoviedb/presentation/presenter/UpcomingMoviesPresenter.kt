package com.taidang.themoviedb.presentation.presenter

import com.taidang.themoviedb.domain.model.Movie
import com.taidang.themoviedb.domain.usecase.GetMoviesUsecase
import com.taidang.themoviedb.presentation.contract.UpcomingMoviesContract
import com.taidang.themoviedb.presentation.manager.AppConfigManager
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class UpcomingMoviesPresenter @Inject constructor(private val getMoviesUsecase: GetMoviesUsecase, private val appConfigManager: AppConfigManager)
    : UpcomingMoviesContract.Presenter {

    override var mView: UpcomingMoviesContract.View? = null
    private val mDisposables = CompositeDisposable()

    override fun start() {
        val disposable = getMoviesUsecase.getUpcomingMovies(appConfigManager.getCurrentCountryCode())
                .doOnSubscribe { mView?.displayLoading() }
                .subscribe(
                        { mView?.displayMovies(it.movies) },
                        { mView?.displayError(it) })
        mDisposables.add(disposable)
    }

    override fun chooseMovie(movie: Movie) {
        mView?.gotoDetailsMovie(movie)
    }

    override fun attachView(v: UpcomingMoviesContract.View) {
        mView = v
    }

    override fun destroy() {
        mView = null
        mDisposables.clear()
    }
}
package com.taidang.themoviedb.presentation.presenter

import com.taidang.themoviedb.domain.usecase.GetMovieDetailsUsecase
import com.taidang.themoviedb.presentation.contract.MovieDetailsContract
import io.reactivex.disposables.CompositeDisposable

class MovieDetailsPresenter(private val getMovieDetailsUsecase: GetMovieDetailsUsecase, private val movieId: Int)
    : MovieDetailsContract.Presenter {

    override var mView: MovieDetailsContract.View? = null
    private val disposables = CompositeDisposable()

    override fun start() {
        val disposable = getMovieDetailsUsecase.getMovieDetails(movieId)
                .doOnSubscribe { mView?.displayLoading() }
                .subscribe(
                        {
                            mView?.run {
                                hideLoading()
                                showDetails(it)
                            }
                        },
                        {
                            mView?.run {
                                hideLoading()
                                displayError(it)
                            }
                        })
        disposables.add(disposable)
    }

    override fun attachView(v: MovieDetailsContract.View) {
        mView = v
    }

    override fun destroy() {
        mView = null
        disposables.clear()
    }
}
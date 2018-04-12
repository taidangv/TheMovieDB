package com.taidang.themoviedb.presentation.presenter

import android.util.Log
import com.taidang.themoviedb.domain.usecase.GetConfigUsecase
import com.taidang.themoviedb.presentation.contract.SplashContract
import io.reactivex.disposables.CompositeDisposable

class SplashPresenter(private val getConfigUsecase: GetConfigUsecase)
    : SplashContract.Presenter {

    private var view: SplashContract.View? = null
    private val disposables = CompositeDisposable()

    override fun fetchConfig() {
        disposables.add(
                getConfigUsecase.getApiConfiguration()
                        .doOnNext(
                                { configs ->
                                    Log.d("taid", "result: $configs")
                                })
                        .subscribe(
                                { _ ->
                                    view?.gotoMainScreen()
                                },
                                { throwable ->
                                    view?.displayError(throwable)
                                })
        )
    }

    override fun attachView(v: SplashContract.View) {
        view = v
    }

    override fun destroy() {
        view = null
        disposables.dispose()
    }
}
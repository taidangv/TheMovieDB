package com.taidang.themoviedb.presentation.presenter

import com.taidang.themoviedb.domain.model.Country
import com.taidang.themoviedb.domain.usecase.GetConfigUsecase
import com.taidang.themoviedb.presentation.contract.SplashContract
import com.taidang.themoviedb.presentation.manager.AppConfigManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.zipWith

class SplashPresenter(private val getConfigUsecase: GetConfigUsecase, private val appConfigManager: AppConfigManager)
    : SplashContract.Presenter {

    private var view: SplashContract.View? = null
    private val disposables = CompositeDisposable()

    override fun fetchConfig() {
        val disposable = getConfigUsecase.getApiConfiguration()
                .zipWith(getConfigUsecase.getCountries())
                .doOnEvent { _, _ ->
                    view?.hideLoading()
                }
                .doOnSuccess { (apiConfigs, countries) ->
                    appConfigManager.saveImagesConfig(apiConfigs.imagesConfig)
                    appConfigManager.saveCountries(countries)
                }
                .subscribe(
                        { (_, countries) -> view?.displayChooseCountryDialog(countries) },
                        { throwable -> view?.displayError(throwable) })

        disposables.add(disposable)
    }

    override fun pickCountry(country: Country) {
        appConfigManager.saveCurrentCountry(country)
        view?.gotoMainScreen()
    }

    override fun attachView(v: SplashContract.View) {
        view = v
    }

    override fun destroy() {
        view = null
        disposables.dispose()
    }
}
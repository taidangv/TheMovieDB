package com.taidang.themoviedb.presentation.presenter

import com.taidang.themoviedb.domain.model.Country
import com.taidang.themoviedb.domain.usecase.GetConfigUsecase
import com.taidang.themoviedb.presentation.contract.SplashContract
import com.taidang.themoviedb.presentation.manager.AppConfigManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.zipWith
import javax.inject.Inject

class SplashPresenter @Inject constructor(private val getConfigUsecase: GetConfigUsecase, private val appConfigManager: AppConfigManager)
    : SplashContract.Presenter {

    override var mView: SplashContract.View? = null
    private val disposables = CompositeDisposable()

    override fun start() {
        fetchConfig()
    }

    private fun fetchConfig() {
        val disposable = getConfigUsecase.getApiConfiguration()
                .zipWith(getConfigUsecase.getCountries())
                .doOnEvent { _, _ ->
                    mView?.hideLoading()
                }
                .doOnSuccess { (apiConfigs, countries) ->
                    with(appConfigManager) {
                        saveImagesConfig(apiConfigs.imagesConfig)
                        saveCountries(countries)
                    }
                }
                .doOnSubscribe { mView?.displayLoading() }
                .subscribe(
                        { (_, countries) -> pickCountryIfAny(countries) },
                        { throwable -> mView?.displayError(throwable) })

        disposables.add(disposable)
    }

    private fun pickCountryIfAny(countries: List<Country>) {
        if (appConfigManager.isFirstTimeLaunchApp())
            mView?.displayChooseCountryDialog(countries)
        else
            mView?.gotoMainScreen()
    }

    override fun pickCountry(country: Country) {
        with(appConfigManager) {
            saveCurrentCountryCode(country.isoCode)
            setLaunchAppAlready()
        }
        mView?.gotoMainScreen()
    }

    override fun attachView(view: SplashContract.View) {
        mView = view
    }

    override fun destroy() {
        mView = null
        disposables.dispose()
    }
}
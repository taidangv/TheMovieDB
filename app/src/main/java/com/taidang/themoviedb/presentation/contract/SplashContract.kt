package com.taidang.themoviedb.presentation.contract

import com.taidang.themoviedb.domain.model.Country

interface SplashContract {

    interface View : IView<Presenter> {
        fun displayLoading()
        fun hideLoading()
        fun displayError(throwable: Throwable)

        fun displayChooseCountryDialog(countries: List<Country>)
        fun gotoMainScreen()
    }

    interface Presenter : IPresenter<View> {
        fun pickCountry(country: Country)
    }
}
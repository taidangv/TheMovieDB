package com.taidang.themoviedb.presentation.contract

import com.taidang.themoviedb.domain.model.Country

interface SplashContract {

    interface View : IView {
        fun displayChooseCountryDialog(countries: List<Country>)
        fun displayLoading()
        fun hideLoading()
        fun gotoMainScreen()
    }

    interface Presenter : IPresenter<View> {
        fun fetchConfig()
        fun pickCountry(country: Country)
    }
}
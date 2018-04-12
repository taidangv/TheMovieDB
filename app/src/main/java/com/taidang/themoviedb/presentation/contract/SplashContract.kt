package com.taidang.themoviedb.presentation.contract

interface SplashContract {

    interface View : IView {
        fun displayLoading()
        fun hideLoading()
        fun gotoMainScreen()
    }

    interface Presenter : IPresenter<View> {
        fun fetchConfig()
    }
}
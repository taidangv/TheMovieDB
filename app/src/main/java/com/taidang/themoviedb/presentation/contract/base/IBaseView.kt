package com.taidang.themoviedb.presentation.contract.base

interface IBaseView<P> {

    var mPresenter: P

    fun displayLoading()
    fun hideLoading()
    fun displayError(throwable: Throwable)
}
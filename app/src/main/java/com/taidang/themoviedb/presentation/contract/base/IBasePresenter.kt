package com.taidang.themoviedb.presentation.contract.base

interface IBasePresenter<V> {

    var mView: V?

    fun attachView(v: V)
    fun start()
    fun destroy()
}
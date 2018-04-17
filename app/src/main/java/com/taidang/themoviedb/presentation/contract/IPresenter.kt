package com.taidang.themoviedb.presentation.contract

interface IPresenter<V> {

    var mView: V?

    fun attachView(v: V)
    fun start()
    fun destroy()
}
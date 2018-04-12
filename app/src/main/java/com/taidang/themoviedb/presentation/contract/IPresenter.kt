package com.taidang.themoviedb.presentation.contract

interface IPresenter<in V> {
    fun attachView(v: V)
    fun destroy()
}
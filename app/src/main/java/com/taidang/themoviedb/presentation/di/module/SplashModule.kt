package com.taidang.themoviedb.presentation.di.module

import com.taidang.themoviedb.presentation.contract.SplashContract
import com.taidang.themoviedb.presentation.presenter.SplashPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class SplashModule {

    @Binds
    abstract fun providesSplashPresenter(p: SplashPresenter): SplashContract.Presenter
}
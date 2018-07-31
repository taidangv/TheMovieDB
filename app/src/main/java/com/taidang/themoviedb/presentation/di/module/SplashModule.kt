package com.taidang.themoviedb.presentation.di.module

import com.taidang.themoviedb.domain.usecase.GetConfigUsecase
import com.taidang.themoviedb.presentation.contract.SplashContract
import com.taidang.themoviedb.presentation.manager.AppConfigManager
import com.taidang.themoviedb.presentation.presenter.SplashPresenter
import dagger.Module
import dagger.Provides

@Module
object SplashModule {

    @JvmStatic
    @Provides
    fun providesSplashPresenter(getConfigUsecase: GetConfigUsecase, appConfigManager: AppConfigManager): SplashContract.Presenter {
        return SplashPresenter(getConfigUsecase, appConfigManager)
    }
}
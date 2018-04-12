package com.taidang.themoviedb.presentation.di.module

import com.taidang.themoviedb.domain.ConfigurationRepository
import com.taidang.themoviedb.domain.SchedulerFactory
import com.taidang.themoviedb.domain.usecase.GetConfigUsecase
import dagger.Module
import dagger.Provides

@Module
class UsecaseModule {

    @Provides
    fun providesGetConfigUsecase(schedulerFactory: SchedulerFactory, configurationRepository: ConfigurationRepository): GetConfigUsecase {
        return GetConfigUsecase(schedulerFactory, configurationRepository)
    }
}
package com.taidang.themoviedb.presentation.di.module

import com.taidang.themoviedb.domain.ConfigurationRepository
import com.taidang.themoviedb.domain.MovieRepository
import com.taidang.themoviedb.domain.SchedulerFactory
import com.taidang.themoviedb.domain.usecase.GetConfigUsecase
import com.taidang.themoviedb.domain.usecase.GetMovieDetailsUsecase
import com.taidang.themoviedb.domain.usecase.GetMoviesUsecase
import dagger.Module
import dagger.Provides

@Module
class UsecaseModule {

    @Provides
    fun providesGetConfigUsecase(schedulerFactory: SchedulerFactory, configurationRepository: ConfigurationRepository): GetConfigUsecase {
        return GetConfigUsecase(schedulerFactory, configurationRepository)
    }

    @Provides
    fun providesGetMoviesUsecase(schedulerFactory: SchedulerFactory, movieRepository: MovieRepository): GetMoviesUsecase {
        return GetMoviesUsecase(schedulerFactory, movieRepository)
    }

    @Provides
    fun providesGetMovieDetailsUsecase(schedulerFactory: SchedulerFactory, movieRepository: MovieRepository): GetMovieDetailsUsecase {
        return GetMovieDetailsUsecase(schedulerFactory, movieRepository)
    }
}
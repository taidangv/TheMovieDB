package com.taidang.themoviedb.domain.usecase

import com.taidang.themoviedb.domain.MovieRepository
import com.taidang.themoviedb.domain.SchedulerFactory
import com.taidang.themoviedb.domain.model.MoviesInfo
import io.reactivex.Single

class GetMoviesUsecase(schedulerFactory: SchedulerFactory, private val movieRepository: MovieRepository)
    : BaseUsecase(schedulerFactory) {

    fun getNowPlayingMovies(countryCode: String, page: Int = 1): Single<MoviesInfo> {
        return movieRepository.getNowPlayingMovies(countryCode, page)
                .subscribeOn(schedulerFactory.workerScheduler())
                .observeOn(schedulerFactory.callbackScheduler())
    }

    fun getUpcomingMovies(countryCode: String, page: Int = 1): Single<MoviesInfo> {
        return movieRepository.getUpcomingMovies(countryCode, page)
                .subscribeOn(schedulerFactory.workerScheduler())
                .observeOn(schedulerFactory.callbackScheduler())
    }
}
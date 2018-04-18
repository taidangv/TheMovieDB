package com.taidang.themoviedb.domain.usecase

import com.taidang.themoviedb.domain.MovieRepository
import com.taidang.themoviedb.domain.SchedulerFactory
import com.taidang.themoviedb.domain.model.Movie
import io.reactivex.Single

class GetMovieDetailsUsecase(schedulerFactory: SchedulerFactory, private val movieRepository: MovieRepository)
    : BaseUsecase(schedulerFactory) {

    fun getMovieDetails(id: Int): Single<Movie> {
        return movieRepository.getMovieDetails(id)
                .subscribeOn(schedulerFactory.workerScheduler())
                .observeOn(schedulerFactory.callbackScheduler())
    }
}
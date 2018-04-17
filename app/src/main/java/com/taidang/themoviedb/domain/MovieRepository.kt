package com.taidang.themoviedb.domain

import com.taidang.themoviedb.domain.model.Movie
import io.reactivex.Single

interface MovieRepository {
    fun getNowPlayingMovies(): Single<List<Movie>>
    fun getUpcomingMovies(): Single<List<Movie>>
}
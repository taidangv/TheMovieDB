package com.taidang.themoviedb.domain

import com.taidang.themoviedb.domain.model.MoviesInfo
import io.reactivex.Single

interface MovieRepository {
    fun getNowPlayingMovies(countryCode: String, page: Int): Single<MoviesInfo>
    fun getUpcomingMovies(countryCode: String, page: Int): Single<MoviesInfo>
}
package com.taidang.themoviedb.repository.http

import com.taidang.themoviedb.repository.response.MovieEntity
import com.taidang.themoviedb.repository.response.MoviesListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieHttpClient {

    @GET("movie/now_playing")
    fun getNowPlayingMovies(@Query("region") countryCode: String, @Query("page") page: Int): Single<MoviesListResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(@Query("region") countryCode: String, @Query("page") page: Int): Single<MoviesListResponse>

    @GET("movie/{id}")
    fun getMovieDetails(@Path("id") id: Int,
                        @Query("language") language: String,
                        @Query("append_to_response") append: String): Single<MovieEntity>
}
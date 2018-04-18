package com.taidang.themoviedb.repository

import com.taidang.themoviedb.domain.MovieRepository
import com.taidang.themoviedb.domain.model.Movie
import com.taidang.themoviedb.domain.model.MoviesInfo
import com.taidang.themoviedb.repository.http.MovieHttpClient
import com.taidang.themoviedb.repository.mapper.MovieMapper
import com.taidang.themoviedb.repository.mapper.MoviesInfoMapper
import io.reactivex.Single

class MovieRemoteDataStore(
        private val movieHttpClient: MovieHttpClient,
        private val moviesInfoMapper: MoviesInfoMapper,
        private val movieMapper: MovieMapper
) : MovieRepository {
    override fun getNowPlayingMovies(countryCode: String, page: Int): Single<MoviesInfo> {
        return movieHttpClient.getNowPlayingMovies(countryCode, page)
                .map { moviesInfoMapper.transform(it) }
    }

    override fun getUpcomingMovies(countryCode: String, page: Int): Single<MoviesInfo> {
        return movieHttpClient.getUpcomingMovies(countryCode, page)
                .map { moviesInfoMapper.transform(it) }
    }

    override fun getMovieDetails(id: Int): Single<Movie> {
        return movieHttpClient.getMovieDetails(id, "en-US", "casts,videos,keywords")
                .map { movieMapper.transform(it) }
    }

}

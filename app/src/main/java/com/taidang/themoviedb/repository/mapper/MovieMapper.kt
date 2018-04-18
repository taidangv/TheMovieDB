package com.taidang.themoviedb.repository.mapper

import com.taidang.themoviedb.domain.model.Movie
import com.taidang.themoviedb.domain.model.MovieDetails
import com.taidang.themoviedb.repository.response.MovieEntity

class MovieMapper(private val detailsMapper: MovieDetailsMapper) : IMapper<MovieEntity, Movie> {
    override fun transform(entity: MovieEntity): Movie {
        val details = transformDetails(entity)
        return Movie(entity.id,
                entity.title,
                entity.release_date.time,
                entity.poster_path,
                entity.backdrop_path,
                entity.adult,
                entity.vote_average,
                details)
    }

    private fun transformDetails(entity: MovieEntity): MovieDetails? {
        return if (hasDetails(entity)) detailsMapper.transform(entity)
        else null
    }

    private fun hasDetails(entity: MovieEntity) = entity.genres != null
}
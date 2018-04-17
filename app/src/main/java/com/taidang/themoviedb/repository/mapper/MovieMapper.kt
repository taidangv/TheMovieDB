package com.taidang.themoviedb.repository.mapper

import com.taidang.themoviedb.domain.model.Movie
import com.taidang.themoviedb.repository.response.MovieEntity

class MovieMapper : IMapper<MovieEntity, Movie> {
    override fun transform(entity: MovieEntity): Movie {
        return Movie(entity.id,
                entity.title,
                entity.release_date.time,
                entity.poster_path,
                entity.backdrop_path,
                entity.overview,
                entity.adult,
                entity.vote_average,
                entity.genre_ids)
    }
}
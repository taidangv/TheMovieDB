package com.taidang.themoviedb.repository.mapper

import com.taidang.themoviedb.domain.model.MoviesInfo
import com.taidang.themoviedb.repository.response.MoviesListResponse

class MoviesInfoMapper(val movieMapper: MovieMapper)
    : IMapper<MoviesListResponse, MoviesInfo> {

    override fun transform(entity: MoviesListResponse): MoviesInfo {
        return MoviesInfo(
                movieMapper.transform(entity.results),
                entity.total_results,
                entity.page,
                entity.total_pages)
    }
}
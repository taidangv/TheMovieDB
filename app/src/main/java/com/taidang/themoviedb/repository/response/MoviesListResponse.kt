package com.taidang.themoviedb.repository.response

class MoviesListResponse(
        results: List<MovieEntity>,
        total_results: Int,
        page: Int,
        total_pages: Int
) : ListBasedResponse<MovieEntity>(results, total_results, page, total_pages)
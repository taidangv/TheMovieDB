package com.taidang.themoviedb.domain.model

data class MoviesInfo(val movies: List<Movie>,
                      val total: Int,
                      val page: Int,
                      val totalPage: Int)
package com.taidang.themoviedb.repository.response

import java.util.*


/* Movie json object
{
      "poster_path": "/pEFRzXtLmxYNjGd0XqJDHPDFKB2.jpg",
      "adult": false,
      "overview": "A lighthouse keeper and his wife living off the coast of Western Australia raise a baby they rescue from an adrift rowboat.",
      "release_date": "2016-09-02",
      "genre_ids": [
        18
      ],
      "id": 283552,
      "original_title": "The Light Between Oceans",
      "original_language": "en",
      "title": "The Light Between Oceans",
      "backdrop_path": "/2Ah63TIvVmZM3hzUwR5hXFg2LEk.jpg",
      "popularity": 4.546151,
      "vote_count": 11,
      "video": false,
      "vote_average": 4.41
}
*/

class MovieEntity(
        val poster_path: String,
        val adult: Boolean,
        val overview: String,
        val release_date: Date,
        val genre_ids: List<Int>,
        val id: Int,
        val original_title: String,
        val original_language: String,
        val title: String,
        val backdrop_path: String,
        val popularity: Double,
        val vote_count: Int,
        val video: Boolean,
        val vote_average: Float)


class MoviesListResponse(
        results: List<MovieEntity>,
        total_results: Int,
        page: Int,
        total_pages: Int
) : ListBasedResponse<MovieEntity>(results, total_results, page, total_pages)
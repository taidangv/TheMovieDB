package com.taidang.themoviedb.repository.response

import com.google.gson.JsonElement
import java.util.*

class MovieEntity(
        // Movie list item
        val id: Int,
        val poster_path: String?,
        val adult: Boolean,
        val release_date: Date,
        val title: String,
        val backdrop_path: String?,
        val vote_count: Int,
        val vote_average: Float,

        // Movie details
        val video: Boolean,
        val homepage: String?,
        val genres: JsonElement?, // too lazy to create entity and mapper
        val overview: String,
        val production_companies: JsonElement, // too lazy to create entity and mapper
        val runtime: Int,
        val status: String,
        val tagline: String?,
        val imdb_id: String,
        val popularity: String
)
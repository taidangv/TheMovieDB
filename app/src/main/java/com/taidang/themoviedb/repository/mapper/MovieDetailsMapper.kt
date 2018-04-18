package com.taidang.themoviedb.repository.mapper

import com.google.gson.JsonElement
import com.taidang.themoviedb.domain.model.Company
import com.taidang.themoviedb.domain.model.MovieDetails
import com.taidang.themoviedb.repository.response.MovieEntity

class MovieDetailsMapper : IMapper<MovieEntity, MovieDetails> {
    override fun transform(entity: MovieEntity): MovieDetails {
        val genres = parseGenres(entity.genres)
        val companies = parseCompanies(entity.production_companies)
        return MovieDetails(
                entity.runtime,
                entity.homepage ?: "",
                genres,
                entity.overview,
                companies,
                entity.status,
                entity.tagline ?: "")
    }

    private fun parseGenres(element: JsonElement): List<String> {
        return if (!element.isJsonArray) emptyList()
        else {
            element.asJsonArray
                    .filter { it.isJsonObject }
                    .map { it.asJsonObject }
                    .filter { !it.get("name").isJsonNull }
                    .map { it.get("name").asString }
        }
    }

    private fun parseCompanies(element: JsonElement): List<Company> {
        return if (!element.isJsonArray) emptyList()
        else {
            element.asJsonArray
                    .filter { it.isJsonObject }
                    .map { it.asJsonObject }
                    .filter { !it.get("logo_path").isJsonNull }
                    .map {
                        Company(it.get("id").asInt, it.get("name").asString, it.get("logo_path").asString)
                    }
        }
    }
}
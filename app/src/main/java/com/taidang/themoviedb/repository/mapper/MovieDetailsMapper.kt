package com.taidang.themoviedb.repository.mapper

import com.google.gson.JsonElement
import com.taidang.themoviedb.domain.model.Company
import com.taidang.themoviedb.domain.model.MovieDetails
import com.taidang.themoviedb.repository.response.MovieEntity

class MovieDetailsMapper(private val castMapper: CastMapper, private val clipMapper: ClipMapper) : IMapper<MovieEntity, MovieDetails> {
    override fun transform(entity: MovieEntity): MovieDetails {
        val genres = parseGenres(entity.genres!!)
        val companies = parseCompanies(entity.production_companies)
        val keywords = parseKeywords(entity.keywords)
        val casts = castMapper.transform(entity.credits.cast)
        val clips = clipMapper.transform(entity.videos.results)
        return MovieDetails(
                entity.runtime,
                genres,
                entity.overview,
                casts,
                clips,
                companies,
                entity.status,
                entity.homepage ?: "",
                entity.tagline ?: "",
                keywords)
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

    private fun parseKeywords(element: JsonElement): List<String> {
        return if (!element.isJsonObject) emptyList()
        else {
            element.asJsonObject.get("keywords").asJsonArray
                    .map { it.asJsonObject }
                    .filter { !it.get("name").isJsonNull }
                    .map { it.get("name").asString }
        }
    }
}
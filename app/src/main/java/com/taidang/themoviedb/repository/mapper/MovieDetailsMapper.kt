package com.taidang.themoviedb.repository.mapper

import com.google.gson.JsonElement
import com.taidang.themoviedb.domain.model.Company
import com.taidang.themoviedb.domain.model.MovieDetails
import com.taidang.themoviedb.repository.response.MovieEntity

class MovieDetailsMapper(private val castMapper: CastMapper, private val clipMapper: ClipMapper) : IMapper<MovieEntity, MovieDetails> {
    override fun transform(entity: MovieEntity): MovieDetails {
        val genres = parseGenres(entity.genres!!)
        val companies = parseCompanies(entity.production_companies)
        val countries = parseCountries(entity.production_countries)
        val keywords = parseKeywords(entity.keywords)
        val casts = castMapper.transform(entity.credits.cast)
        val clips = clipMapper.transform(entity.videos.results)
        val contentRating = parseCertification(entity.release_dates)
        return MovieDetails(
                entity.runtime,
                genres,
                entity.overview,
                casts,
                clips,
                companies,
                countries,
                entity.status,
                entity.homepage ?: "",
                entity.tagline ?: "",
                keywords,
                contentRating)
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

    private fun parseCountries(element: JsonElement): List<String> {
        return if (!element.isJsonArray) emptyList()
        else {
            element.asJsonArray
                    .filter { it.isJsonObject }
                    .map { it.asJsonObject }
                    .filter { !it.get("name").isJsonNull }
                    .map { it.get("name").asString }
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

    private fun parseCertification(element: JsonElement, country: String = "US"): String? {
        return if (!element.isJsonObject) ""
        else {
            element.asJsonObject.get("results").asJsonArray
                    .map { it.asJsonObject }
                    .filter { it.get("iso_3166_1").asString.equals(country, true) }
                    .flatMap { it.get("release_dates").asJsonArray }
                    .filter {
                        val value: JsonElement = it.asJsonObject.get("certification")
                        value.isJsonPrimitive && value.asString.isNotEmpty()
                    }
                    .map { it.asJsonObject.get("certification").asString }
                    .singleOrNull()
        }
    }
}
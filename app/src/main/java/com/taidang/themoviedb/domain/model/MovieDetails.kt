package com.taidang.themoviedb.domain.model

data class MovieDetails(val duration: Int,
                        val genres: List<String>,
                        val description: String,
                        val casts: List<Cast>,
                        val clips: List<Clip>,
                        val companies: List<Company>,
                        val countries: List<String>,
                        val status: String,
                        val homepage: String,
                        val tagline: String,
                        val keywords: List<String>,
                        val contentRating: String?) {

    fun getDurationStr(): String {
        val hour = duration / 60
        val min = duration % 60
        return if (hour <= 0) "$min min"
        else "$hour hour $min min"
    }
}
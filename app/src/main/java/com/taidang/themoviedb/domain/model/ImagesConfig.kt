package com.taidang.themoviedb.domain.model

data class ImagesConfig(val baseUrl: String,
                        val backdropSizes: List<String>,
                        val logoSizes: List<String>,
                        val posterSizes: List<String>,
                        val profileSizes: List<String>) {

    fun buildBackdropUrl(backdropPath: String?): String? {
        return if (backdropSizes.isNotEmpty() && backdropPath != null)
            baseUrl + backdropSizes[0] + backdropPath
        else
            null
    }

    fun buildPosterUrl(posterPath: String?): String? {
        return if (posterSizes.isNotEmpty() && posterPath != null)
            baseUrl + posterSizes[posterSizes.size - 1] + posterPath
        else
            null
    }

}
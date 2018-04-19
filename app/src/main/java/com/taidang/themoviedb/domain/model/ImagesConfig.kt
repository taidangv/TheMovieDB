package com.taidang.themoviedb.domain.model

data class ImagesConfig(private val baseUrl: String,
                        private val mapBackdropSizes: Map<ImageSize, String>,
                        private val mapLogoSizes: Map<ImageSize, String>,
                        private val mapPosterSizes: Map<ImageSize, String>,
                        private val mapProfileSizes: Map<ImageSize, String>) {

    fun buildBackdropUrl(backdropPath: String?, size: ImageSize = ImageSize.MEDIUM): String? {
        return backdropPath?.let {
            baseUrl + mapBackdropSizes[size] + it
        }
    }

    fun buildPosterUrl(posterPath: String?, size: ImageSize = ImageSize.MEDIUM): String? {
        return posterPath?.let {
            baseUrl + mapPosterSizes[size] + it
        }
    }

    fun buildProfileUrl(profilePath: String?, size: ImageSize = ImageSize.MEDIUM): String? {
        return profilePath?.let {
            baseUrl + mapProfileSizes[size] + it
        }
    }

    fun buildLogoUrl(logoPath: String?, size: ImageSize = ImageSize.MEDIUM): String? {
        return logoPath?.let {
            baseUrl + mapLogoSizes[size] + it
        }
    }

}
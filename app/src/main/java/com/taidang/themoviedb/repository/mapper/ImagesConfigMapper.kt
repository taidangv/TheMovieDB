package com.taidang.themoviedb.repository.mapper

import com.taidang.themoviedb.domain.model.ImageSize
import com.taidang.themoviedb.domain.model.ImagesConfig
import com.taidang.themoviedb.repository.response.ImagesConfigEntity

class ImagesConfigMapper : IMapper<ImagesConfigEntity, ImagesConfig> {

    override fun transform(entity: ImagesConfigEntity): ImagesConfig {
        with(entity) {
            val mapBackdropSizes = mapOf(
                    ImageSize.SMALL to getSizeWithFallback(backdrop_sizes, "w300"),
                    ImageSize.MEDIUM to getSizeWithFallback(backdrop_sizes, "w780"),
                    ImageSize.LARGE to getSizeWithFallback(backdrop_sizes, "w1280"),
                    ImageSize.ORIGINAL to getSizeWithFallback(backdrop_sizes, "original")
            )
            val mapLogoSizes = mapOf(
                    ImageSize.SMALL to getSizeWithFallback(logo_sizes, "w45"),
                    ImageSize.MEDIUM to getSizeWithFallback(logo_sizes, "w185"),
                    ImageSize.LARGE to getSizeWithFallback(logo_sizes, "w500"),
                    ImageSize.ORIGINAL to getSizeWithFallback(logo_sizes, "original")
            )
            val mapPosterSizes = mapOf(
                    ImageSize.SMALL to getSizeWithFallback(poster_sizes, "w154"),
                    ImageSize.MEDIUM to getSizeWithFallback(poster_sizes, "w342"),
                    ImageSize.LARGE to getSizeWithFallback(poster_sizes, "w780"),
                    ImageSize.ORIGINAL to getSizeWithFallback(poster_sizes, "original")
            )
            val mapProfileSizes = mapOf(
                    ImageSize.SMALL to getSizeWithFallback(profile_sizes, "w45"),
                    ImageSize.MEDIUM to getSizeWithFallback(profile_sizes, "w185"),
                    ImageSize.LARGE to getSizeWithFallback(profile_sizes, "h632"),
                    ImageSize.ORIGINAL to getSizeWithFallback(profile_sizes, "original")
            )
            return ImagesConfig(
                    entity.secure_base_url,
                    mapBackdropSizes,
                    mapLogoSizes,
                    mapPosterSizes,
                    mapProfileSizes)
        }
    }


    private fun getSizeWithFallback(listOfSize: List<String>, targetSize: String): String = when {
        listOfSize.isEmpty() -> ""
        listOfSize.contains(targetSize) -> targetSize
        listOfSize.contains("original") -> "original"
        else -> listOfSize[listOfSize.size - 1]
    }

}
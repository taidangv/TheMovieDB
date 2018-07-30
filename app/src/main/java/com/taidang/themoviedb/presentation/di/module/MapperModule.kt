package com.taidang.themoviedb.presentation.di.module

import com.taidang.themoviedb.repository.mapper.*
import dagger.Module
import dagger.Provides

@Module
object MapperModule {

    @JvmStatic
    @Provides
    fun providesImagesMapper() = ImagesConfigMapper()

    @JvmStatic
    @Provides
    fun providesApiConfigurationMapper(imagesConfigMapper: ImagesConfigMapper) = ApiConfigurationMapper(imagesConfigMapper)

    @JvmStatic
    @Provides
    fun providesCountryMapper() = CountryMapper()

    @JvmStatic
    @Provides
    fun prviodesCastMapper() = CastMapper()

    @JvmStatic
    @Provides
    fun prviodesClipMapper() = ClipMapper()

    @JvmStatic
    @Provides
    fun providesMovieDetailsMapper(castMapper: CastMapper, clipMapper: ClipMapper) = MovieDetailsMapper(castMapper, clipMapper)

    @JvmStatic
    @Provides
    fun providesMovieMapper(detailsMapper: MovieDetailsMapper) = MovieMapper(detailsMapper)

    @JvmStatic
    @Provides
    fun providesMoviesInfoMapper(movieMapper: MovieMapper) = MoviesInfoMapper(movieMapper)

}
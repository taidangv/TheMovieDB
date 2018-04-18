package com.taidang.themoviedb.presentation.di.module

import com.taidang.themoviedb.repository.mapper.*
import dagger.Module
import dagger.Provides

@Module
class MapperModule {

    @Provides
    fun providesImagesMapper() = ImagesConfigMapper()

    @Provides
    fun providesApiConfigurationMapper(imagesConfigMapper: ImagesConfigMapper) = ApiConfigurationMapper(imagesConfigMapper)

    @Provides
    fun providesCountryMapper() = CountryMapper()

    @Provides
    fun providesMovieDetailsMapper() = MovieDetailsMapper()

    @Provides
    fun providesMovieMapper(detailsMapper: MovieDetailsMapper) = MovieMapper(detailsMapper)

    @Provides
    fun providesMoviesInfoMapper(movieMapper: MovieMapper) = MoviesInfoMapper(movieMapper)

}
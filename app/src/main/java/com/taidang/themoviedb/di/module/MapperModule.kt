package com.taidang.themoviedb.di.module

import com.taidang.themoviedb.repository.mapper.ApiConfigurationMapper
import com.taidang.themoviedb.repository.mapper.CountryMapper
import com.taidang.themoviedb.repository.mapper.ImagesConfigMapper
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

}
package com.taidang.themoviedb.repository.mapper

import com.taidang.themoviedb.domain.model.ApiConfiguration
import com.taidang.themoviedb.repository.response.ApiConfigurationEntity

class ApiConfigurationMapper(private val imagesMapper: ImagesConfigMapper) : IMapper<ApiConfigurationEntity, ApiConfiguration> {

    override fun transform(entity: ApiConfigurationEntity): ApiConfiguration {
        val imagesConfig = imagesMapper.transform(entity.images)
        return ApiConfiguration(imagesConfig)
    }
}
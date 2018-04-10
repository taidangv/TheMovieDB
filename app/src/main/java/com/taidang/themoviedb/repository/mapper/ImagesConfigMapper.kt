package com.taidang.themoviedb.repository.mapper

import com.taidang.themoviedb.domain.model.ImagesConfig
import com.taidang.themoviedb.repository.response.ImagesConfigEntity

class ImagesConfigMapper : IMapper<ImagesConfigEntity, ImagesConfig> {

    override fun transform(entity: ImagesConfigEntity): ImagesConfig {
        return ImagesConfig(
                entity.secure_base_url,
                entity.backdrop_sizes,
                entity.logo_sizes,
                entity.poster_sizes,
                entity.profile_sizes)
    }

}
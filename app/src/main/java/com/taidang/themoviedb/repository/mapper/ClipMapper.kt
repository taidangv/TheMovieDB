package com.taidang.themoviedb.repository.mapper

import com.taidang.themoviedb.domain.model.Clip
import com.taidang.themoviedb.repository.response.ClipEntity

class ClipMapper : IMapper<ClipEntity, Clip> {
    override fun transform(entity: ClipEntity) = Clip(
            entity.id,
            entity.key,
            entity.name,
            entity.site,
            entity.type,
            entity.size
    )

}
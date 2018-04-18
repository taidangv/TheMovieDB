package com.taidang.themoviedb.repository.mapper

import com.taidang.themoviedb.domain.model.Cast
import com.taidang.themoviedb.repository.response.CastEntity

class CastMapper : IMapper<CastEntity, Cast> {
    override fun transform(entity: CastEntity) = Cast(
            entity.id,
            entity.name,
            entity.character,
            entity.profile_path,
            entity.cast_id,
            entity.credit_id,
            entity.order)
}
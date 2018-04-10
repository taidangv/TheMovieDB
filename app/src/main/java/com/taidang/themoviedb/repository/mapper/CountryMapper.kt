package com.taidang.themoviedb.repository.mapper

import com.taidang.themoviedb.domain.model.Country
import com.taidang.themoviedb.repository.response.CountryEntity

class CountryMapper : IMapper<CountryEntity, Country> {
    override fun transform(entity: CountryEntity): Country {
        return Country(entity.iso_3166_1, entity.english_name)
    }
}
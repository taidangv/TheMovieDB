package com.taidang.themoviedb.repository.mapper

// Transform repository's entities to domain's models

interface IMapper<in E, out M> {

    fun transform(entity: E): M

    fun transform(listOfE: List<E>): List<M> {
        return listOfE.map { transform(it) }
    }
}
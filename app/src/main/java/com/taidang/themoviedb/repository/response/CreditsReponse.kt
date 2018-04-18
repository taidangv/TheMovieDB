package com.taidang.themoviedb.repository.response

class CastEntity(
        val id: Int,
        val name: String,
        val character: String,
        val cast_id: Int,
        val credit_id: String,
        val gender: Int,
        val order: Int,
        val profile_path: String?
)

class CrewEntity(
        val id: Int,
        val name: String,
        val profile_path: String?,
        val department: String,
        val job: String,
        val gender: Int
)

class CreditsResponse(
        val cast: List<CastEntity>,
        val crew: List<CrewEntity>
)
package com.taidang.themoviedb.repository.response

class ClipEntity(
        val id: String,
        val key: String,
        val name: String,
        val site: String,
        val type: String,
        val size: Int
)

class ClipResponse(val results: List<ClipEntity>)
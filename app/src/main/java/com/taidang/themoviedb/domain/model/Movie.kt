package com.taidang.themoviedb.domain.model

data class Movie(val id: Int,
                 val title: String,
                 val releaseDate: Long,
                 val posterPath: String?,
                 val backdropPath: String?,
                 val shortenDescription: String,
                 val isAdultContent: Boolean,
                 val vote: Float,
                 val genreIds: List<Int>) {

    fun getTMDbRating() = "TMDb $vote"

}
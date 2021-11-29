package com.marcos.projects.http.dto

import arrow.core.toOption
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.marcos.projects.model.Movie
import java.util.Date

@JsonIgnoreProperties(ignoreUnknown = true)
data class MovieBody(
    val imdbId: String,
    val rating: Double?
) {
    fun toMovie() = Movie(
        imdbId, rating.toOption()
    )

    constructor(movie: Movie) : this(
        movie.imdbId,
        movie.rating.orNull()
    )
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class CompleteMovieResponse(
    val imdbId : String,
    val rating : Double,
    val name: String,
    val description: String,
    val releaseDate: String,
    val imdbRating : String,
    val runtime: String
)


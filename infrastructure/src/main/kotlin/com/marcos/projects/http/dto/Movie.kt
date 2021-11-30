package com.marcos.projects.http.dto

import arrow.core.toOption
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.marcos.projects.model.CompleteMovie
import com.marcos.projects.model.ImdbMovie
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
) {
    constructor(completeMovie: CompleteMovie) : this(
        completeMovie.imdbId,
        completeMovie.rating,
        completeMovie.name,
        completeMovie.description,
        completeMovie.releaseDate,
        completeMovie.imdbRating,
        completeMovie.runtime
    )

    fun toCompleteMovie() = CompleteMovie(
        imdbId = imdbId,
        rating = rating,
        name = name,
        description = description,
        releaseDate = releaseDate,
        imdbRating = imdbRating,
        runtime = runtime
    )
}


@JsonIgnoreProperties(ignoreUnknown = true)
data class ImdbMovieResponse(
    @JsonProperty("Title")
    val name : String,
    @JsonProperty("Plot")
    val description: String,
    @JsonProperty("Released")
    val releaseDate: String,
    @JsonProperty("imdbRating")
    val imdbRating: String,
    @JsonProperty("Runtime")
    val runtime: String
) {
    fun toImdbMovie() = ImdbMovie(
        name = name,
        description = description,
        releaseDate = releaseDate,
        imdbRating = imdbRating,
        runtime = runtime
    )
}


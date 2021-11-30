package com.marcos.projects.model

import arrow.core.Option
import arrow.core.getOrElse

data class Movie (
    val imdbId : String,
    val rating : Option<Double>,
    val numberOfVotes: Int
)

data class CompleteMovie(
    val imdbId : String,
    val rating : Double,
    val name: String,
    val description: String,
    val releaseDate: String,
    val imdbRating : String,
    val runtime: String
) {
    constructor(movie: Movie, imdbMovie: ImdbMovie): this(
        imdbId = movie.imdbId,
        rating = movie.rating.getOrElse { 0.0 },
        name = imdbMovie.name,
        description = imdbMovie.description,
        releaseDate = imdbMovie.releaseDate,
        imdbRating = imdbMovie.imdbRating,
        runtime = imdbMovie.runtime
    )
}

data class ImdbMovie(
    val name : String,
    val description: String,
    val releaseDate: String,
    val imdbRating: String,
    val runtime: String
)


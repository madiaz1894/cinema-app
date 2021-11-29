package com.marcos.projects.model

import arrow.core.Option

data class Movie (
    val imdbId : String,
    val rating : Option<Double>
)

data class CompleteMovie(
    val imdbId : String,
    val rating : Double,
    val name: String,
    val description: String,
    val releaseDate: String,
    val imdbRating : String,
    val runtime: String
)


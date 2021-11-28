package com.marcos.projects.http.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.marcos.projects.model.Movie
import java.util.Date

@JsonIgnoreProperties(ignoreUnknown = true)
data class MovieResponse(
    val id: Long,
    val name : String,
    val rating : Double,
    val numberOfVotes : Int,
    val runtime: Int,
    val imdbId: String,
    val releaseDate: Date
) {
    data class Prototype(
        val name : String,
        val rating : Double,
        val numberOfVotes : Int,
        val runtime: Int,
        val imdbId: String,
        val releaseDate: Date
    ) {
        constructor(movie: Movie): this(
            name = movie.name,
            rating = movie.rating,
            numberOfVotes = movie.numberOfVotes,
            runtime = movie.runtime,
            imdbId = movie.imdbId,
            releaseDate = movie.releaseDate
        )
    }

}

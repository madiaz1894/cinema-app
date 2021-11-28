package com.marcos.projects.model

import java.util.Date

class Movie (
    val id : Long,
    val name : String,
    val rating : Double,
    val numberOfVotes : Int,
    val runtime: Int,
    val imdbId: String,
    val releaseDate: Date
    ) {
    class Prototype (
        val name : String,
        val rating : Double,
        val numberOfVotes : Int,
        val runtime: Int,
        val imdbId: String,
        val releaseDate: Date
    )
}
package com.marcos.projects.model

data class MovieTimes (
    val movie: Movie,
    val times: List<MovieSchedule>
)

data class MovieSchedule (
    val showTime: String,
    val day: String,
    val price: Double
)
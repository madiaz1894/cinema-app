package com.marcos.projects.model

data class MovieTimes (
    val id: Long,
    val movieName: String,
    val times: List<MovieSchedule>
)

data class MovieSchedule (
    val showTime: String,
    val price: String
)
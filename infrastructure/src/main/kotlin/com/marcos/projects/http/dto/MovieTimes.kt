package com.marcos.projects.http.dto

import com.marcos.projects.model.MovieSchedule
import com.marcos.projects.model.MovieTimes


data class MovieTimesResponse (
    val movie: MovieBody,
    val times: List<MovieScheduleResponse>
) {
    constructor(movieTimes : MovieTimes): this(
        movie = MovieBody(movieTimes.movie),
        times = movieTimes.times.map { MovieScheduleResponse(it) }
    )

    fun toMoviesTime() = MovieTimes(
        movie = this.movie.toMovie(),
        times = this.times.map { it.toMovieSchedule() }
    )

}

data class MovieScheduleResponse (
    val showTime: String,
    val day: String,
    val price: Double
) {
    constructor(movieSchedule: MovieSchedule): this(
        showTime = movieSchedule.showTime,
        day = movieSchedule.day,
        price = movieSchedule.price
    )

    fun toMovieSchedule () = MovieSchedule(
        showTime = this.showTime,
        day = this.day,
        price = this.price
    )
}
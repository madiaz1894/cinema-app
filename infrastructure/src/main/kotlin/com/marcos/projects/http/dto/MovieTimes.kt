package com.marcos.projects.http.dto

import com.marcos.projects.model.MovieSchedule
import com.marcos.projects.model.MovieTimes


class MovieTimesResponse (
    val id: Long,
    val movieName: String,
    val times: List<MovieScheduleResponse>
) {
    constructor(movieTimes : MovieTimes): this(
        id = movieTimes.id,
        movieName = movieTimes.movieName,
        times = movieTimes.times.map { MovieScheduleResponse(it) }
    )

    fun toMoviesTime() = MovieTimes(
        id = this.id,
        movieName = this.movieName,
        times = this.times.map { it.toMovieSchedule() }
    )

}

class MovieScheduleResponse (
    val showTime: String,
    val price: String
) {
    constructor(movieSchedule: MovieSchedule): this(
        showTime = movieSchedule.showTime,
        price = movieSchedule.price
    )

    fun toMovieSchedule () = MovieSchedule(
        showTime = this.showTime,
        price = this.price
    )
}
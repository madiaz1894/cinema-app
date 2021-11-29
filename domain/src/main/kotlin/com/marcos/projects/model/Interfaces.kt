package com.marcos.projects.model


interface MoviesRepository {
    fun createMovie(movie: Movie)
    fun upsertMovieTimes(movieId: String, movieTimes: List<MovieSchedule>)
    fun getMovieTimes(imdbId : String): MovieTimes
    fun getMovieDetail(imdbId: String): CompleteMovie
}

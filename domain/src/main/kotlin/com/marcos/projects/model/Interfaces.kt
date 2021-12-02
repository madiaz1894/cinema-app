package com.marcos.projects.model


interface MoviesRepository {
    fun createMovie(movie: Movie)
    fun upsertMovieTimes(movieId: String, movieTimes: List<MovieSchedule>)
    fun getMovie(movieId: String): Movie
    fun getMovieTimes(imdbId : String): MovieTimes
    fun getAllMovies(): List<Movie>
    fun updateRating(imdbId: String, rating : Double, numberOfVotes: Int)
}

interface MovieDetailService {
    fun getMovieDetail(imdbId: String): ImdbMovie
}

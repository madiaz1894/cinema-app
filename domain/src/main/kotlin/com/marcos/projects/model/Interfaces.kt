package com.marcos.projects.model


interface MoviesRepository {
    fun createMovie()
    fun getMovieTimes(name : String): MovieTimes
}

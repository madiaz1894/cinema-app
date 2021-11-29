package com.marcos.projects.actions.movies

import com.marcos.projects.Result
import com.marcos.projects.loggerFor
import com.marcos.projects.model.MovieSchedule
import com.marcos.projects.model.MoviesRepository
import com.marcos.projects.resultOf

class UpsertMovieTimes (private val repository: MoviesRepository) {

    fun execute(movieId: String, movieTimes: List<MovieSchedule>) : Result<Unit> {
        logger.info("Upserting Showtimes for movie: ${movieId}")
        return resultOf { repository.upsertMovieTimes(movieId, movieTimes) }
    }

    companion object {
        private val logger = loggerFor<CreateMovie>()
    }
}
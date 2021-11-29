package com.marcos.projects.actions.movies

import com.marcos.projects.loggerFor
import com.marcos.projects.model.Movie
import com.marcos.projects.model.MoviesRepository
import com.marcos.projects.resultOf
import com.marcos.projects.Result

class CreateMovie (private val repository: MoviesRepository) {

    fun execute(movie: Movie) : Result<Unit> {
        logger.info("Creating Movie: ${movie.imdbId}")
        return resultOf { repository.createMovie(movie) }
    }

    companion object {
        private val logger = loggerFor<CreateMovie>()
    }
}
package com.marcos.projects.actions.movies

import com.marcos.projects.loggerFor
import com.marcos.projects.model.MoviesRepository
import com.marcos.projects.resultOf

class GetAllMovies (private val repository: MoviesRepository) {

    fun execute() = resultOf {
        logger.info("Getting all movies in database")
        repository.getAllMovies()
    }

    companion object {
        private val logger = loggerFor<GetAllMovies>()
    }
}
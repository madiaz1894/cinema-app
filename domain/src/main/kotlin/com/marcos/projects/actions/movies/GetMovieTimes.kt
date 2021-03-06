package com.marcos.projects.actions.movies

import com.marcos.projects.loggerFor
import com.marcos.projects.model.MoviesRepository
import com.marcos.projects.resultOf

class GetMovieTimes (private val repository: MoviesRepository) {

    fun execute(imdbId: String) = resultOf {
        logger.info("Getting movie times for: $imdbId")
        repository.getMovieTimes(imdbId)
    }

    companion object {
        private val logger = loggerFor<GetMovieTimes>()
    }
}
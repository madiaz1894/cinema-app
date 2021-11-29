package com.marcos.projects.actions.movies

import com.marcos.projects.loggerFor
import com.marcos.projects.model.MoviesRepository
import com.marcos.projects.resultOf

class GetMovieDetail (private val repository: MoviesRepository) {

    fun execute(imdbId: String) = resultOf {
        logger.info("Getting movie detail for: $imdbId")
        repository.getMovieDetail(imdbId)
    }

    companion object {
        private val logger = loggerFor<GetMovieDetail>()
    }
}
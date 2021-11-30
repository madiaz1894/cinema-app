package com.marcos.projects.actions.movies

import com.marcos.projects.loggerFor
import com.marcos.projects.model.CompleteMovie
import com.marcos.projects.model.MovieDetailService
import com.marcos.projects.resultOf
import com.marcos.projects.Result
import com.marcos.projects.model.MoviesRepository

class GetMovieDetail (private val service: MovieDetailService, private val repository: MoviesRepository) {

    fun execute(imdbId: String): Result<CompleteMovie> {
        return resultOf {
            logger.info("Getting movie rating for: $imdbId")
            repository.getMovie(imdbId)
        }.bimap(
            {
                    throwable -> throw throwable
            }, {
            logger.info("Getting imdb movie detail for: $imdbId")
            val imdbMovieDetail = service.getMovieDetail(imdbId)
            CompleteMovie(it, imdbMovieDetail)
        })
    }

    companion object {
        private val logger = loggerFor<GetMovieDetail>()
    }
}
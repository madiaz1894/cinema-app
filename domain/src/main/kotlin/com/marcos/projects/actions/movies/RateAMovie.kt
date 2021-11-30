package com.marcos.projects.actions.movies

import arrow.core.getOrElse
import com.marcos.projects.RatingOutOfBoundException
import com.marcos.projects.Result
import com.marcos.projects.loggerFor
import com.marcos.projects.model.MoviesRepository
import com.marcos.projects.resultOf

class RateAMovie (private val repository: MoviesRepository) {

    fun execute(movieId: String, rating: Int) : Result<Double> {
        return resultOf {
            if (rating > 5 || rating < 0) {
                throw RatingOutOfBoundException(rating.toString())
            }
            logger.info("Getting movie rating for: $movieId")
            repository.getMovie(movieId)
        }.bimap(
            {
                    throwable -> throw throwable
            }, {
                val newRating = (rating.toDouble() + it.rating.getOrElse { 5.0 })/ 2
                repository.updateRating(movieId, newRating, it.numberOfVotes + 1)
                newRating
            })
    }

    companion object {
        private val logger = loggerFor<RateAMovie>()
    }
}
package com.marcos.projects.repositories

import arrow.core.getOrElse
import arrow.core.toOption
import com.marcos.projects.EntityNotFoundException
import com.marcos.projects.UnprocessedEntity
import com.marcos.projects.http.dto.MovieScheduleResponse
import com.marcos.projects.loggerFor
import com.marcos.projects.model.*
import com.marcos.projects.repositories.mappers.movieMapper
import com.marcos.projects.repositories.mappers.movieScheduleResponseMapper
import com.marcos.projects.repositories.mappers.movieTimesMapper
import com.marcos.projects.repositories.queries.*
import com.marcos.projects.repositories.queries.FIND_MOVIE_ID_NAME
import com.marcos.projects.repositories.queries.FIND_MOVIE_TIMES_BY_ID
import com.marcos.projects.repositories.queries.INSERT_MOVIE
import com.marcos.projects.repositories.queries.INSERT_MOVIE_TIMES_AND_PRIZES
import org.springframework.dao.DataAccessException
import org.springframework.dao.DuplicateKeyException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import javax.transaction.Transactional

internal open class SQLMovieTimesRepository (
    private val database: NamedParameterJdbcTemplate): MoviesRepository {

    @Transactional
    override fun createMovie(movie: Movie) {
        try {
            logger.info("creating movie: ${movie.imdbId}")
            val mapParams: MutableMap<String, Any?> = HashMap()
            mapParams["imdbId"] = movie.imdbId
            mapParams["name"] = movie.name
            mapParams["rating"] = movie.rating.getOrElse { 5.0 }
            val params = MapSqlParameterSource()
                .addValues(mapParams)
            database.update(INSERT_MOVIE, params)
        } catch (exception: Exception) {
            when (exception) {
                is DuplicateKeyException -> throw UnprocessedEntity(
                    "The movie with the id ${movie.imdbId} already exist",
                    exception
                )
                is DataAccessException ->  throw UnprocessedEntity(
                    "The movie cannot be inserted into postgresql database", exception
                )
                else -> {
                    throw exception
                }
            }
        }
    }

    @Transactional
    override fun upsertMovieTimes(movieId: String, movieTimes: List<MovieSchedule>) {
        try {
            logger.info("Upserting movie times for movie: $movieId")
            for (movieSchedule in movieTimes) {
                val mapParams: MutableMap<String, Any?> = HashMap()
                mapParams["movieId"] = movieId
                mapParams["day"] = movieSchedule.day
                mapParams["showTime"] = movieSchedule.showTime
                mapParams["price"] = movieSchedule.price
                val params = MapSqlParameterSource()
                    .addValues(mapParams)
                database.update(INSERT_MOVIE_TIMES_AND_PRIZES, params)
            }
        } catch (e: DataAccessException) {
            throw UnprocessedEntity(
                "The movie cannot be inserted into postgresql database", e
            )
        }
    }

    @Transactional
    override fun getMovie(movieId: String): Movie {
        return try {
            database.queryForObject(
                FIND_MOVIE_ID_NAME,
                mapOf("imdbId" to movieId),
                movieMapper()
            )?.toMovie().toOption()
                .getOrElse { throw EntityNotFoundException("The movie with id: $movieId does not exist") }

        } catch (exception: Exception) {
            when (exception) {
                is EmptyResultDataAccessException -> throw EntityNotFoundException("The movie with name $movieId does not exist")
                else -> {
                    throw exception
                }
            }
        }
    }

    @Transactional
    override fun getMovieTimes(imdbId: String): MovieTimes {
        return try {
            database.queryForObject(
                FIND_MOVIE_ID_NAME,
                mapOf("imdbId" to imdbId),
                movieTimesMapper(this::findMovieTimes)
            )?.toMoviesTime().toOption()
                .getOrElse { throw EntityNotFoundException("The movie with id: $imdbId does not exist") }

        } catch (exception: Exception) {
            when (exception) {
                is EmptyResultDataAccessException -> throw EntityNotFoundException("The movie with id $imdbId does not exist")
                else -> {
                    throw exception
                }
            }
        }
    }

    @Transactional
    override fun updateRating(imdbId: String, rating: Double, numberOfVotes: Int) {
        try {
            logger.info("Updating rating for movie: $imdbId")
            val mapParams: MutableMap<String, Any?> = HashMap()
            mapParams["movieId"] = imdbId
            mapParams["rating"] = rating
            mapParams["numberOfVotes"] = numberOfVotes

            val params = MapSqlParameterSource()
                .addValues(mapParams)
            database.update(UPDATE_RATING_FOR_MOVIE, params)
        } catch (e: DataAccessException) {
            throw UnprocessedEntity(
                "The movie cannot be inserted into postgresql database", e
            )
        }
    }

    private fun findMovieTimes(movieId: String): List<MovieScheduleResponse> {
        val params = mapOf("id" to movieId)
        return database.query(
            FIND_MOVIE_TIMES_BY_ID,
            params,
            movieScheduleResponseMapper()
        )
    }

    companion object {
        private val logger = loggerFor<SQLMovieTimesRepository>()
    }
}
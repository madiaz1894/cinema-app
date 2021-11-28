package com.marcos.projects.repositories

import arrow.core.getOrElse
import arrow.core.toOption
import com.marcos.projects.EntityNotFoundException
import com.marcos.projects.http.dto.MovieScheduleResponse
import com.marcos.projects.model.MovieTimes
import com.marcos.projects.model.MoviesRepository
import com.marcos.projects.repositories.mappers.movieScheduleResponseMapper
import com.marcos.projects.repositories.mappers.movieTimesMapper
import com.marcos.projects.repositories.queries.FIND_MOVIE_ID_NAME
import com.marcos.projects.repositories.queries.FIND_MOVIE_TIMES_BY_ID
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

class SQLMovieTimesRepository (
    private val database: NamedParameterJdbcTemplate): MoviesRepository {


    override fun getMovieTimes(name: String): MovieTimes {
        return try {
            database.queryForObject(
                FIND_MOVIE_ID_NAME,
                mapOf("name" to name),
                movieTimesMapper(this::findMovieTimes)
            )?.toMoviesTime().toOption()
                .getOrElse { throw EntityNotFoundException("The movie with name: $name does not exist") }

        } catch (exception: Exception) {
            when (exception) {
                is EmptyResultDataAccessException -> throw EntityNotFoundException("The movie with name $name does not exist")
                else -> {
                    throw exception
                }
            }
        }
    }

    private fun findMovieTimes(movieId: Long): List<MovieScheduleResponse> {
        val params = mapOf("id" to movieId)
        return database.query(
            FIND_MOVIE_TIMES_BY_ID,
            params,
            movieScheduleResponseMapper()
        )
    }
}
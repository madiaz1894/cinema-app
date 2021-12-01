package com.marcos.projects.http

import arrow.core.getOrHandle
import com.marcos.projects.actions.movies.CreateMovie
import com.marcos.projects.actions.movies.UpsertMovieTimes
import com.marcos.projects.http.dto.MovieBody
import com.marcos.projects.http.dto.MovieScheduleResponse
import com.marcos.projects.loggerFor
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/internal/movies", produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
class InternalMoviesController(
    private val createMovie: CreateMovie,
    private val upsertMovieTimes: UpsertMovieTimes
) {
    @PostMapping("/create")
    fun createMovie(@RequestBody movie: MovieBody){
        createMovie.execute(movie.toMovie()).getOrHandle {
            logger.error(CustomExceptionHandler.getMessage(it))
            throw it
        }
    }

    @PostMapping("/{movieId}/times")
    fun createMovieTimes(@PathVariable movieId : String, @RequestBody movieTimes: List<MovieScheduleResponse>) {
        upsertMovieTimes.execute(movieId, movieTimes.map { it.toMovieSchedule() }).getOrHandle {
            logger.error(CustomExceptionHandler.getMessage(it))
            throw it
        }
    }

    companion object {
        private val logger = loggerFor<InternalMoviesController>()
    }
}
package com.marcos.projects.http

import arrow.core.getOrHandle
import com.marcos.projects.actions.movies.CreateMovie
import com.marcos.projects.actions.movies.UpsertMovieTimes
import com.marcos.projects.http.dto.MovieBody
import com.marcos.projects.http.dto.MovieScheduleResponse
import com.marcos.projects.loggerFor
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PathVariable

@RestController
@RequestMapping("/internal/movies", produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
@Api(value = "admin", description = "Rest API for administrative operations create ", tags = ["Admin API"])
class InternalMoviesController(
    private val createMovie: CreateMovie,
    private val upsertMovieTimes: UpsertMovieTimes
) {
    @PostMapping("/create")
    @ApiOperation(value = "Create a movie in the local Database")
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "OK"),
            ApiResponse(code = 401, message = "You are not authorized access the resource"),
            ApiResponse(code = 404, message = "The resource not found")
        ]
    )
    fun createMovie(@RequestBody movie: MovieBody){
        createMovie.execute(movie.toMovie()).getOrHandle {
            logger.error(CustomExceptionHandler.getMessage(it))
            throw it
        }
    }



    @PostMapping("/{imdbId}/times")
    @ApiOperation(value = "Create a movie show times and prices for each show time")
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "OK"),
            ApiResponse(code = 401, message = "You are not authorized access the resource"),
            ApiResponse(code = 404, message = "The resource not found")
        ]
    )
    fun createMovieTimes(@PathVariable imdbId : String, @RequestBody movieTimes: List<MovieScheduleResponse>) {
        upsertMovieTimes.execute(imdbId, movieTimes.map { it.toMovieSchedule() }).getOrHandle {
            logger.error(CustomExceptionHandler.getMessage(it))
            throw it
        }
    }

    companion object {
        private val logger = loggerFor<InternalMoviesController>()
    }
}
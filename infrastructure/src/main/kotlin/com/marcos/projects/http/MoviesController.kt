package com.marcos.projects.http

import arrow.core.getOrHandle
import com.marcos.projects.actions.movies.GetAllMovies
import com.marcos.projects.actions.movies.GetMovieDetail
import com.marcos.projects.actions.movies.GetMovieTimes
import com.marcos.projects.actions.movies.RateAMovie
import com.marcos.projects.http.dto.CompleteMovieResponse
import com.marcos.projects.http.dto.MovieBody
import com.marcos.projects.http.dto.MovieTimesResponse
import com.marcos.projects.loggerFor
import com.marcos.projects.model.Movie
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("movies", produces = [MediaType.APPLICATION_JSON_VALUE])
@Api(value = "public", description = "Public API to fetch movies show times and detail ", tags = ["Public API"])
class MoviesController (
    private val getMovieTimes: GetMovieTimes,
    private val getMovieDetail: GetMovieDetail,
    private val rateAMovie: RateAMovie,
    private val getAllMovies: GetAllMovies
    ) {

    @GetMapping()
    @ApiOperation(value = "Get all movies in database")
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "OK"),
            ApiResponse(code = 401, message = "You are not authorized access the resource"),
            ApiResponse(code = 404, message = "The resource not found")
        ]
    )
    fun getMovies(): List<MovieBody> {
        return getAllMovies.execute().getOrHandle {
            logger.error(CustomExceptionHandler.getMessage(it))
            throw it
        }.map { MovieBody(it) }
    }

    @GetMapping("/{imdbId}/times")
    @ApiOperation(value = "Get movie times and prices for a specified movie")
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "OK"),
            ApiResponse(code = 401, message = "You are not authorized access the resource"),
            ApiResponse(code = 404, message = "The resource not found")
        ]
    )
    fun getMovieTimes(@PathVariable imdbId : String): MovieTimesResponse {
        return MovieTimesResponse(getMovieTimes.execute(imdbId).getOrHandle {
            logger.error(CustomExceptionHandler.getMessage(it))
            throw it
        })
    }

    @GetMapping("/{imdbId}/detail")
    @ApiOperation(value = "Get a detailed view of a movie")
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "OK"),
            ApiResponse(code = 401, message = "You are not authorized access the resource"),
            ApiResponse(code = 404, message = "The resource not found")
        ]
    )
    fun getMovieDetail(@PathVariable imdbId : String): CompleteMovieResponse {
        return CompleteMovieResponse(getMovieDetail.execute(imdbId).getOrHandle {
            logger.error(CustomExceptionHandler.getMessage(it))
            throw it
        })
    }

    @PutMapping("/{imdbId}/rating/{rating}")
    @ApiOperation(value = "Rate a movie with 0 to 5 stars")
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "OK"),
            ApiResponse(code = 401, message = "You are not authorized access the resource"),
            ApiResponse(code = 404, message = "The resource not found")
        ]
    )
    fun rateAMovie(@PathVariable imdbId : String, @PathVariable rating : Int): Double {
        return rateAMovie.execute(imdbId, rating).getOrHandle {
            logger.error(CustomExceptionHandler.getMessage(it))
            throw it
        }
    }

    companion object {
        private val logger = loggerFor<MoviesController>()
    }
}

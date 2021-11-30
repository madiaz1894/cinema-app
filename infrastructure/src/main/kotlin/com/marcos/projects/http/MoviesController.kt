package com.marcos.projects.http

import arrow.core.getOrHandle
import com.marcos.projects.actions.movies.*
import com.marcos.projects.http.dto.CompleteMovieResponse
import com.marcos.projects.http.dto.MovieBody
import com.marcos.projects.http.dto.MovieScheduleResponse
import com.marcos.projects.http.dto.MovieTimesResponse
import com.marcos.projects.loggerFor
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("movies", produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
class MoviesController (
    private val getMovieTimes: GetMovieTimes,
    private val createMovie: CreateMovie,
    private val upsertMovieTimes: UpsertMovieTimes,
    private val getMovieDetail: GetMovieDetail,
    private val rateAMovie: RateAMovie
    ) {

    @GetMapping("/{movieName}/times")
    fun getMovieTimes(@PathVariable movieName : String): MovieTimesResponse {
        return MovieTimesResponse(getMovieTimes.execute(movieName).getOrHandle {
            logger.error(CustomExceptionHandler.getMessage(it))
            throw it
        })
    }

    @GetMapping("/{imdbId}/detail")
    fun getMovieDetail(@PathVariable imdbId : String): CompleteMovieResponse {
        return CompleteMovieResponse(getMovieDetail.execute(imdbId).getOrHandle {
            logger.error(CustomExceptionHandler.getMessage(it))
            throw it
        })
    }

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

    @PutMapping("/{movieId}/rating/{rating}")
    fun rateAMovie(@PathVariable movieId : String, @PathVariable rating : Int): Double {
        return rateAMovie.execute(movieId, rating).getOrHandle {
            logger.error(CustomExceptionHandler.getMessage(it))
            throw it
        }
    }


    companion object {
        private val logger = loggerFor<MoviesController>()
    }

}

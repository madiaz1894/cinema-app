package com.marcos.projects.http

import arrow.core.getOrHandle
import com.marcos.projects.actions.movies.CreateMovie
import com.marcos.projects.actions.movies.GetMovieTimes
import com.marcos.projects.actions.movies.UpsertMovieTimes
import com.marcos.projects.http.dto.MovieBody
import com.marcos.projects.http.dto.MovieScheduleResponse
import com.marcos.projects.http.dto.MovieTimesResponse
import com.marcos.projects.loggerFor
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody


@RestController
@RequestMapping("movies")
class MoviesController (
    private val getMovieTimes: GetMovieTimes,
    private val createMovie: CreateMovie,
    private val upsertMovieTimes: UpsertMovieTimes
    ) {

    @GetMapping("/{movieName}/times")
    fun getMovieTimes(@PathVariable movieName : String): MovieTimesResponse {
        return MovieTimesResponse(getMovieTimes.execute(movieName).getOrHandle {
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


    companion object {
        private val logger = loggerFor<MoviesController>()
    }

}

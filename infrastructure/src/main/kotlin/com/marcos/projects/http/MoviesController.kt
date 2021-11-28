package com.marcos.projects.http

import arrow.core.getOrHandle
import com.marcos.projects.actions.movies.GetMovieTimes
import com.marcos.projects.http.dto.MovieTimesResponse
import com.marcos.projects.loggerFor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("movies")
class MoviesController (
    private val getMovieTimes: GetMovieTimes
    ) {

    @GetMapping("/{movieName}/times")
    fun getMovieTimes(@PathVariable movieName : String): MovieTimesResponse {
        return MovieTimesResponse(getMovieTimes.execute(movieName).getOrHandle {
            logger.error(CustomExceptionHandler.getMessage(it))
            throw it
        })
    }


    companion object {
        private val logger = loggerFor<MoviesController>()
    }

}

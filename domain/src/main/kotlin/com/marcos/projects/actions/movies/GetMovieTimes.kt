package com.marcos.projects.actions.movies

import com.marcos.projects.model.MoviesRepository
import com.marcos.projects.resultOf

class GetMovieTimes (private val repository: MoviesRepository) {

    fun execute(name: String) = resultOf {
        repository.getMovieTimes(name)
    }
}
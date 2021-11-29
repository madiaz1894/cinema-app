package com.marcos.projects.config

import com.marcos.projects.actions.movies.CreateMovie
import com.marcos.projects.actions.movies.GetMovieDetail
import com.marcos.projects.actions.movies.GetMovieTimes
import com.marcos.projects.actions.movies.UpsertMovieTimes
import com.marcos.projects.model.MoviesRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
internal class ActionsConfig {

    @Bean
    fun getMovieTimes(moviesRepository: MoviesRepository) =
        GetMovieTimes(moviesRepository)

    @Bean
    fun createMovie(moviesRepository: MoviesRepository) =
        CreateMovie(moviesRepository)

    @Bean
    fun upsertMovieTimes(moviesRepository: MoviesRepository) =
        UpsertMovieTimes(moviesRepository)

    @Bean
    fun getMovieDetail(moviesRepository: MoviesRepository) =
        GetMovieDetail(moviesRepository)
}

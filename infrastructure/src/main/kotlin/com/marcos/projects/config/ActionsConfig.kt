package com.marcos.projects.config

import com.marcos.projects.actions.movies.GetMovieTimes
import com.marcos.projects.model.MoviesRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
internal class ActionsConfig {

    @Bean
    fun getMovieTimes(moviesRepository: MoviesRepository) =
        GetMovieTimes(moviesRepository)

}

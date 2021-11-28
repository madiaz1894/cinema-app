package com.marcos.projects.config

import com.marcos.projects.repositories.SQLMovieTimesRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

@Configuration
internal class RepositoriesConfig(
    private val database: NamedParameterJdbcTemplate
) {
    @Bean
    internal fun moviesRepository() =
        SQLMovieTimesRepository(database)

}
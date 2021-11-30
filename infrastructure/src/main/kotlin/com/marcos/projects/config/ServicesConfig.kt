package com.marcos.projects.config

import com.marcos.projects.services.HttpImdbService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
internal class ServicesConfig(
    private val restTemplate: RestTemplate,
    @Value("\${app.imdb.api-key}")
    private val apiKey: String,
    @Value("\${app.imdb.get-movie-detail-url}")
    private val getMovieDetailUrl: String
) {
    @Bean
    internal fun imdbService() =
        HttpImdbService(restTemplate, getMovieDetailUrl ,apiKey)

}
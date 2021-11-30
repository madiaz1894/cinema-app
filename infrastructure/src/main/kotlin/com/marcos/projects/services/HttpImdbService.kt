package com.marcos.projects.services

import com.marcos.projects.EntityNotFoundException
import com.marcos.projects.http.dto.ImdbMovieResponse
import com.marcos.projects.loggerFor
import com.marcos.projects.model.ImdbMovie
import com.marcos.projects.model.MovieDetailService
import org.springframework.web.client.RestTemplate

internal open class HttpImdbService (
    private val restTemplate: RestTemplate,
    private val getMovieDetailUrl: String,
    private val apiKey : String): MovieDetailService {


    override fun getMovieDetail(imdbId: String): ImdbMovie {
        val url = getMovieDetailUrl
            .replace(":apiKey", apiKey)
            .replace(":imdbId", imdbId)
        logger.info("Getting movie details for: $imdbId")
        val response = restTemplate.getForObject(url, ImdbMovieResponse::class.java)
        if (response != null) {
            return response.toImdbMovie()
        }
        throw EntityNotFoundException("The entity was not found in the ImdbService")
    }

    companion object {
        private val logger = loggerFor<HttpImdbService>()
    }

}
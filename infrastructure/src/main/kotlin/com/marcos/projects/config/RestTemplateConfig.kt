package com.marcos.projects.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.web.client.RestTemplate
import java.time.Duration

@Configuration
internal class RestTemplateConfig(
    @Value("\${app.application-id}")
    private val applicationId: String,
    @Value("\${app.connect-timeout}")
    private val connectionTimeout: Long,
    @Value("\${app.read-timeout}")
    private val readTimeout: Long
) {

    @Bean
    @Primary
    fun restTemplate(): RestTemplate = RestTemplateBuilder()
        .setConnectTimeout(Duration.ofMillis(connectionTimeout))
        .setReadTimeout(Duration.ofMillis(readTimeout))
        .build()

}

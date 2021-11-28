package com.marcos.projects.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor

@Configuration
internal class AsyncConfig {

    @Bean("generalExecutor")
    fun asyncThreadPoolGeneral(): Executor {
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = 1
        executor.maxPoolSize = 10
        executor.setThreadNamePrefix("generalExecutor-")
        executor.setQueueCapacity(10)
        executor.initialize()
        return executor
    }

    /**
     * Please don't use me for general purpose
     * **/
    @Bean("processesExecutor")
    fun threadPoolExecutorProcess(@Value("\${app.max-processes-thread-executing}") threads: Int): ThreadPoolExecutor {
        return Executors.newFixedThreadPool(threads) as ThreadPoolExecutor
    }

    /**
     * Please don't use me for general purpose
     * **/
    @Bean("itemsExecutor")
    fun singleThreadPoolExecutorItems(@Value("\${app.max-items-thread-executing}") threads: Int): ThreadPoolExecutor {
        return Executors.newFixedThreadPool(threads) as ThreadPoolExecutor
    }
}

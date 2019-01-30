package com.example.demo

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
open class SpidersApplication {

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(SpidersApplication::class.java)
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(SpidersApplication::class.java, *args)
            LOGGER.info("Boot Server started.")
        }
    }
}

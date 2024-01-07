package com.sdt.proshop.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories("com.sdt.proshop.repositories")
@EnableMongoAuditing
class AppConfig {

    private val timeConverters: List<Converter<*, *>> = mutableListOf()

    @Bean
    fun objectMapper(): ObjectMapper {
        val mapper = jacksonObjectMapper()
        mapper.registerKotlinModule()
        mapper.registerModule(JavaTimeModule())
        return mapper
    }

    @Bean
    fun timeConversions(): MongoCustomConversions {
        timeConverters.addFirst(InstantReadConverter())
        timeConverters.addLast(InstantWriteConverter())
        return MongoCustomConversions(timeConverters)
    }
}
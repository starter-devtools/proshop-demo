package com.sdt.proshop.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.sdt.proshop.models.Product
import com.sdt.proshop.repositories.ProductRepository
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component
import java.io.File

@Component
class Seeder(
    private val mapper: ObjectMapper,
    private val productRepository: ProductRepository
): ApplicationListener<ContextRefreshedEvent> {
    private val log = LoggerFactory.getLogger(Seeder::class.java)

    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        log.debug("In SeedData.onApplicationEvent()");
        readJson()
    }

    private fun readJson() {
//        val resource: Resource = ClassPathResource("classpath:products.json")
//        val file = resource.file
//        val json = file.readText()
        val json: String = File("backend/src/main/resources/products.json").readText(Charsets.UTF_8)
        val products = mapper.readValue<List<Product>>(json)
        productRepository.insert(products)
    }
}
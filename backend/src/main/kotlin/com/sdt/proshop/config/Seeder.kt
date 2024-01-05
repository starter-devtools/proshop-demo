package com.sdt.proshop.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.sdt.proshop.models.Product
import com.sdt.proshop.repositories.ProductRepository
import com.sdt.proshop.services.ProductService
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component

@Component
class Seeder(
    private val mapper: ObjectMapper,
    private val productService: ProductService
): ApplicationListener<ContextRefreshedEvent> {

    private val log = LoggerFactory.getLogger(Seeder::class.java)

    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        log.debug("In SeedData.onApplicationEvent()");
//        readJson()
    }

    private fun readJson() {
        val resource: Resource = ClassPathResource("products.json")
        val file = resource.file
        val json = file.readText()

        val products = mapper.readValue<List<Product>>(json)
        productService.saveAll(products)
    }
}
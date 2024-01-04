package com.sdt.proshop.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.sdt.proshop.models.Product
import com.sdt.proshop.services.ProductService
import org.springframework.stereotype.Service
import java.io.File


@Service
class ProductServiceImpl(
    private val mapper: ObjectMapper
): ProductService {

    override fun list(): List<Product> {
        return this.readJson();
    }

    override fun get(id: String): Product? {
        val list = this.readJson();
        return list.find { id.contentEquals(it.id) }
    }

    private fun readJson(): List<Product> {
//        val resource: Resource = ClassPathResource("classpath:products.json")
//        val file = resource.file
//        val json = file.readText()
        val json: String = File("backend/src/main/resources/products.json").readText(Charsets.UTF_8)
        return mapper.readValue<List<Product>>(json)
    }

}

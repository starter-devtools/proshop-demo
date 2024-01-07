package com.sdt.proshop.services

import com.sdt.proshop.models.Product
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import java.math.BigDecimal

@DataMongoTest
class ProductServiceIT {

    @Autowired
    private lateinit var productService: ProductService

    @Test
    fun getProducts() {
        //create
        val product = Product(
            name = "",
            image = "",
            description = "",
            brand = "",
            category = "",
            price = BigDecimal.TEN,
            countInStock = 1,
            rating = 0.00,
            numReviews = 0
        )

        //save
        this.productService.saveAll(listOf(product))

        //get
        val list = this.productService.list()
        assertThat(list).isNotEmpty
    }
}
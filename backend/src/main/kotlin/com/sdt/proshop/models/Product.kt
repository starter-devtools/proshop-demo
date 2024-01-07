package com.sdt.proshop.models

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.Instant

@Document("products")
class Product(
    @Id val id: String? = null,
    var name: String,
    var image: String,
    var description: String,
    var brand: String,
    var category: String,
    var price: BigDecimal,
    var countInStock: Int,
    var rating: Double,
    var numReviews: Int,
    val createdAt: Instant = Instant.now(),
    var updatedAt: Instant? = null,
    @Version val version: Int = 0
)
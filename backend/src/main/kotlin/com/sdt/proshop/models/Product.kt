package com.sdt.proshop.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal

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
)
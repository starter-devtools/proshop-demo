package com.sdt.proshop.models

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

class Product(
    @JsonProperty("_id") val id: String? = null,
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
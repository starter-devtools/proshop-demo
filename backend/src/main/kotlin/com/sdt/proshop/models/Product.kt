package com.sdt.proshop.models

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.Instant

@Document("products")
class Product(
    @Id val id: String? = null,
    @Indexed(name = "_name_", unique = true)
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

data class ProductDto(
    val id: String? = null,
    val name: String,
    val image: String,
    val description: String,
    val brand: String,
    val category: String,
    val price: BigDecimal,
    val countInStock: Int,
    val rating: Double,
    val numReviews: Int,
) {
    constructor(product: Product): this(
        id = product.id,
        name = product.name,
        image = product.image,
        description = product.description,
        brand = product.brand,
        category = product.category,
        price = product.price,
        countInStock = product.countInStock,
        rating = product.rating,
        numReviews = product.numReviews,
    )
}
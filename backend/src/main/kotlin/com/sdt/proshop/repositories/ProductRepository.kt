package com.sdt.proshop.repositories

import com.sdt.proshop.models.Product
import org.springframework.data.mongodb.repository.MongoRepository

interface ProductRepository: MongoRepository<Product, String>
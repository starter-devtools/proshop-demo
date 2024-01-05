package com.sdt.proshop.repositories

import com.sdt.proshop.models.Product
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

interface ProductRepository: MongoRepository<Product, String>
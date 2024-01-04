package com.sdt.proshop.services.impl;

import com.sdt.proshop.models.Product
import com.sdt.proshop.repositories.ProductRepository
import com.sdt.proshop.services.ProductService
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl(
    private val repository: ProductRepository
): ProductService {

    override fun list(): List<Product> = repository.findAll();

    override fun get(id: String): Product? = repository.findById(id)
        .orElseThrow {IllegalArgumentException("(Product id=$id) not found.")}

}

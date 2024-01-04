package com.sdt.proshop.services

import com.sdt.proshop.models.Product

interface ProductService {

    fun list(): List<Product>
    fun get(id: String): Product?

}
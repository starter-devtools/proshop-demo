package com.sdt.proshop.controllers

import com.sdt.proshop.services.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/products")
class ProductController(
    private val productService: ProductService
) {

    @GetMapping
    fun list() = ResponseEntity.ok(productService.list())

    @GetMapping("/{id}")
    fun get(@PathVariable id: String) = ResponseEntity.ok(productService.get(id))

}
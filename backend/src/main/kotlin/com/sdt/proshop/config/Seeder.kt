package com.sdt.proshop.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.sdt.proshop.models.Product
import com.sdt.proshop.security.models.Role
import com.sdt.proshop.security.models.User
import com.sdt.proshop.security.models.UserRole
import com.sdt.proshop.security.repositories.UserRepository
import com.sdt.proshop.services.ProductService
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class Seeder(
    private val mapper: ObjectMapper,
    private val productService: ProductService,
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository,
): ApplicationListener<ContextRefreshedEvent> {

    private val log = LoggerFactory.getLogger(Seeder::class.java)

    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        log.debug("In SeedData.onApplicationEvent()");
//        loadProducts()
//        loadUsers()
    }

    private fun loadProducts() {
        val json = readJson("products")
        val products = mapper.readValue<List<Product>>(json);
        productService.saveAll(products)
    }

    private fun loadUsers() {
        val json = readJson("users")
        val users = mapper.readValue<List<User>>(json);
        users.forEach {
            it.credentials = this.passwordEncoder.encode(it.password)
            it.userRoles = if (it.isAdmin) {
              mutableSetOf(UserRole(Role("ROLE_ADMIN")))
            } else {
              mutableSetOf(UserRole(Role("ROLE_USER")))
            }
        }
        this.userRepository.saveAll(users)
    }

    private fun readJson(fileName: String): String {
        val resource: Resource = ClassPathResource("data/${fileName}.json")
        val file = resource.file
        return file.readText()
    }
}
package com.sdt.proshop.security.controllers

import com.sdt.proshop.constants.ADMIN_ROLE
import com.sdt.proshop.security.services.AdminService
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
@Secured(ADMIN_ROLE)
class AdminController(
    private val adminService: AdminService
) {

    @GetMapping
    fun listUsers(@PathVariable id: String) = adminService.list()

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: String) = adminService.getById(id)



}
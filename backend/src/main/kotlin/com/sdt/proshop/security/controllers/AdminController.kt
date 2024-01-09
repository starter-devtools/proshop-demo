package com.sdt.proshop.security.controllers

import com.sdt.proshop.constants.ADMIN_ROLE
import com.sdt.proshop.constants.USER_ROLE
import com.sdt.proshop.security.models.UserDto
import com.sdt.proshop.security.services.AdminService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
//@Secured(ADMIN_ROLE) //TODO: Admin role is ignored by security?
@Secured(value = [USER_ROLE, ADMIN_ROLE])
class AdminController(
    private val adminService: AdminService
) {

    @GetMapping
    fun listUsers(): ResponseEntity<List<UserDto>> {
        val list = adminService.list().asSequence().map { UserDto(it) }.toList()
        return ResponseEntity.ok(list)
    }

//    @Secured(value = [USER_ROLE, ADMIN_ROLE])
    @GetMapping("/{id}")
    fun getUser(@PathVariable id: String): ResponseEntity<UserDto> {
        val user = this.adminService.getById(id)!!
        return ResponseEntity.ok(UserDto(user))
    }

//    @Secured(value = [USER_ROLE, ADMIN_ROLE])
    @PatchMapping("/{id}")
    fun updatePassword(@PathVariable id: String, @RequestBody userDto: UserDto): ResponseEntity<Unit> {
        this.adminService.updatePassword(id, userDto)
        return ResponseEntity.accepted().build()
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: String, @RequestBody userDto: UserDto): ResponseEntity<Unit> {
        this.adminService.update(id, userDto)
        return ResponseEntity.accepted().build()
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: String): ResponseEntity<Unit> {
        this.adminService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
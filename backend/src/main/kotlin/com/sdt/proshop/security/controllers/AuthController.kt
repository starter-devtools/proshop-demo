package com.sdt.proshop.security.controllers

import com.sdt.proshop.security.models.LoginDto
import com.sdt.proshop.security.models.UserDto
import com.sdt.proshop.security.services.AuthService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping(value = ["/register", "/signup"])
    fun registration(@RequestBody userDto: UserDto): ResponseEntity<String> {
        val response = authService.register(userDto)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @PostMapping(value = ["/login", "/signin"])
    fun login(@RequestBody loginDto: LoginDto): ResponseEntity<String> {
        val response = authService.login(loginDto)
        return ResponseEntity(response, HttpStatus.ACCEPTED)
    }

    @PostMapping(value = ["/logout", "/signout"])
    fun logout(request: HttpServletRequest): ResponseEntity<Unit> {
        authService.logout(request)
        return ResponseEntity.ok().build()
    }

}
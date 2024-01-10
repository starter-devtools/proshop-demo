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
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = ["/register"])
    fun registration(@RequestBody userDto: UserDto): Unit = authService.register(userDto)

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(value = ["/login"])
    fun login(@RequestBody loginDto: LoginDto): String = authService.login(loginDto)

    @PostMapping(value = ["/logout", "/signout"])
    fun logout(request: HttpServletRequest): ResponseEntity<Unit> {
        authService.logout(request)
        return ResponseEntity.ok().build()
    }

}
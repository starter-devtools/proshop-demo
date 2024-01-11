package com.sdt.proshop.security.controllers

import com.sdt.proshop.security.models.LoginDto
import com.sdt.proshop.security.models.UserDto
import com.sdt.proshop.security.services.AuthService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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
    fun login(@RequestBody loginDto: LoginDto, response: HttpServletResponse): ResponseEntity<Unit> {
        val jwt: String = this.authService.login(loginDto)

//        val cookie = Cookie("accessToken", jwt);
//        cookie.isHttpOnly = true
//        response.addCookie(cookie)

        val headers = HttpHeaders()
        headers.setBearerAuth(jwt)
        return ResponseEntity.ok().headers(headers).build()
    }

    @PostMapping(value = ["/logout"])
    fun logout(request: HttpServletRequest): ResponseEntity<Unit> {
        authService.logout(request)
        return ResponseEntity.ok().build()
    }

}
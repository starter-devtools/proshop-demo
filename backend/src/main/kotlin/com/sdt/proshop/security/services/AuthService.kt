package com.sdt.proshop.security.services

import com.sdt.proshop.security.models.LoginDto
import com.sdt.proshop.security.models.UserDto
import jakarta.servlet.http.HttpServletRequest

interface AuthService {

    fun register(userDto: UserDto)
    fun login(loginDto: LoginDto): String
    fun logout(request: HttpServletRequest)

}
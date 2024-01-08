package com.sdt.proshop.security.services

import com.sdt.proshop.security.models.LoginDto
import com.sdt.proshop.security.models.UserDto

interface AuthService {

    fun register(userDto: UserDto): String
    fun login(loginDto: LoginDto): String

}
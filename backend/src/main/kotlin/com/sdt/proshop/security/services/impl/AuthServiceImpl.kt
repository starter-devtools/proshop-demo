package com.sdt.proshop.security.services.impl

import com.sdt.proshop.security.models.UserDto
import com.sdt.proshop.security.repositories.UserRepository
import com.sdt.proshop.security.services.AuthService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
): AuthService {

    override fun register(userDto: UserDto): String {
//        require(this.getByEmail(userDto.email) == null) { "Email ${userDto.email} already exists!" }
        userDto.password = this.passwordEncoder.encode(userDto.password)
        val user = com.sdt.proshop.security.models.User(userDto)
        this.userRepository.save(user)
        return ""
    }

    override fun login(userDto: UserDto): String {
        TODO("Not yet implemented")
    }
}
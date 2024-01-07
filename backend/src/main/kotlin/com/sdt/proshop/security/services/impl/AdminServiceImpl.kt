package com.sdt.proshop.security.services.impl

import com.sdt.proshop.security.models.User
import com.sdt.proshop.security.models.UserDto
import com.sdt.proshop.security.repositories.UserRepository
import com.sdt.proshop.security.services.AdminService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class AdminServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
): AdminService {

    override fun list(): List<User> = this.userRepository.findAll()

    override fun getByEmail(email: String): User? = this.userRepository.findUserByUsername(email)

    override fun update(userDto: UserDto) {
        val user = User(userDto)
        if (!user.password.contentEquals(userDto.password)) {
            user.password = this.passwordEncoder.encode(userDto.password)
        }

        user.updatedAt = Instant.now()
        this.userRepository.save(user)
    }

    override fun delete(email: String) {
        val user = this.getByEmail(email)
        if (user != null) {
            this.userRepository.deleteById(user.id!!)
        }
    }
}
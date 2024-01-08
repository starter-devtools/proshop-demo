package com.sdt.proshop.security.services.impl

import com.sdt.proshop.exceptions.ResourceNotFoundException
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

    override fun getById(id: String): User? = this.userRepository.findById(id)
        .orElseThrow { throw ResourceNotFoundException("User", "ID", id) }

    override fun getByEmail(email: String): User? = this.userRepository.findUserByEmail(email)
        ?: throw ResourceNotFoundException("User", "Email", email)

    override fun update(userDto: UserDto) {
        val user = User(userDto)
        if (!user.password.contentEquals(userDto.password)) {
            user.credentials = this.passwordEncoder.encode(userDto.password)
        }

        user.updatedAt = Instant.now()
        this.userRepository.save(user)
    }

    override fun delete(id: String) {
        val user = this.getById(id)
        if (user != null) {
            this.userRepository.deleteById(user.id!!)
        }
    }
}
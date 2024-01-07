package com.sdt.proshop.security.services.impl

import com.sdt.proshop.security.repositories.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository
): UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepository.findUserByUsername(username!!)
        val authorities = mutableSetOf<GrantedAuthority>()

        user.authorities.forEach {
            authorities.add(SimpleGrantedAuthority(it.authority))
        }

        return User(user.email, user.password, authorities)
    }

}
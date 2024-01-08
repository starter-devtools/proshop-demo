package com.sdt.proshop.security.services.impl

import com.sdt.proshop.extensions.checkSelf
import com.sdt.proshop.security.repositories.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository
): UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        val input = username.checkSelf() ?: throw IllegalArgumentException("'$username' is not a valid.")

        val user = userRepository.findUserByEmail(input)
            ?: throw UsernameNotFoundException("User with email=$input not found!")

        //Spring Security expects these roles to be granted authorities
        val authorities: Set<GrantedAuthority> = user.userRoles.asSequence()
            .map { SimpleGrantedAuthority(it.authority) }.toSet()

        return org.springframework.security.core.userdetails.User(user.email, user.credentials, authorities)
    }

}
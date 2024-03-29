package com.sdt.proshop.security.services.impl

import com.sdt.proshop.constants.ADMIN_ROLE
import com.sdt.proshop.constants.USER_ROLE
import com.sdt.proshop.exceptions.DuplicateResourceException
import com.sdt.proshop.extensions.checkSelf
import com.sdt.proshop.security.config.JwtTokenProvider
import com.sdt.proshop.security.models.*
import com.sdt.proshop.security.repositories.UserRepository
import com.sdt.proshop.security.services.AuthService
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val authenticationManager: AuthenticationManager,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider
): AuthService {

    private val log = LoggerFactory.getLogger(AuthServiceImpl::class.java)

    override fun register(userDto: UserDto) {
        val email: String = userDto.email.checkSelf()!!

        //Check if email already exists
        if (this.userRepository.findUserByEmail(email) != null) {
            throw DuplicateResourceException("User", "Email", email)
        }

        val user = User(
            name = userDto.name!!,
            email = email,
            credentials = this.passwordEncoder.encode(userDto.password),
            isAdmin = userDto.isAdmin
        )

        if (user.isAdmin) {
            user.userRoles = mutableSetOf(Role(ADMIN_ROLE))
        } else {
            user.userRoles = mutableSetOf(Role(USER_ROLE))
        }

        this.userRepository.save(user)
    }

    /**
     * Authenticates the user
     * @param loginDto the user's credentials
     * @return a jwt
     */
    override fun login(loginDto: LoginDto): String {
        log.info("Attempting to log in... ${loginDto.email}")

        //Create a new authentication for SS
        val authentication: Authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(loginDto.email, loginDto.password)
        )

        //Add the authentication to the security context
        SecurityContextHolder.getContext().authentication = authentication

        //Create the jwt
        //TODO: Add JWT as HTTP-ONly Cookie? Use protected routes in React?
        return this.jwtTokenProvider.generateToken(authentication)
    }

    /**
     * Removes the JWT, and logs out the user
     * @param request the HTTP request
     * @return a jwt
     */
    override fun logout(request: HttpServletRequest) {
        this.jwtTokenProvider.removeToken(request)

        //Remove the authenticated user
        SecurityContextHolder.clearContext()
    }
}
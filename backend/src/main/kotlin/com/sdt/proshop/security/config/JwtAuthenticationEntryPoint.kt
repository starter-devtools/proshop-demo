package com.sdt.proshop.security.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class JwtAuthenticationEntryPoint: AuthenticationEntryPoint {

    /**
     * Adds JWT error handling to Spring Security
     */
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {
        //Sends 401 when user does not have valid JWT
        response?.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException?.message)
    }

}
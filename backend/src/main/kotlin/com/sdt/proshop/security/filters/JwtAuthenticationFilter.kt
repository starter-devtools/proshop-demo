package com.sdt.proshop.security.filters

import com.sdt.proshop.constants.BEARER_PREFIX
import com.sdt.proshop.security.config.JwtTokenProvider
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtTokenProvider: JwtTokenProvider,
    private val userDetailsService: UserDetailsService
): OncePerRequestFilter() {

    private val log = LoggerFactory.getLogger(JwtAuthenticationFilter::class.java)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        //0. Check request header for token
        val authHeader = request.getHeader(HttpHeaders.AUTHORIZATION)
        if (authHeader.doesNotContainBearerToken()) {
            log.info("No token!")
            filterChain.doFilter(request, response)
            return
        }

        //1. Get token from the request
        val jwt = authHeader!!.extractTokenValue()

        //2. validate token
        if (this.jwtTokenProvider.isValid(jwt)) {
            //3. extract username from token
            val email = this.jwtTokenProvider.extractEmail(jwt)

            //4. get user from DB - cache this?
            val userDetails: UserDetails = this.userDetailsService.loadUserByUsername(email)

            //5. Build user authn
            val authToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
            authToken.details = WebAuthenticationDetailsSource().buildDetails(request)

            //6. authn user in security context
            SecurityContextHolder.getContext().authentication = authToken

            //7. continue the filter chain
            filterChain.doFilter(request, response)
        } else {
            log.info("Invalid token!")
            // Token is blacklisted or expired, deny access
            response.status = HttpServletResponse.SC_UNAUTHORIZED;
        }
    }

    private fun String?.doesNotContainBearerToken() = this == null || !this.startsWith(BEARER_PREFIX)
    private fun String.extractTokenValue() = this.substringAfter(BEARER_PREFIX)

}
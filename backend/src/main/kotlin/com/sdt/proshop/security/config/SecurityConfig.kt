package com.sdt.proshop.security.config

import com.sdt.proshop.security.filters.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
class SecurityConfig(
    private val userDetailsService: UserDetailsService,
    private val authenticationEntryPoint: JwtAuthenticationEntryPoint,
    private val authenticationFilter: JwtAuthenticationFilter
) {

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    //Automatically uses the userDetailsService and passwordEncoder
    @Bean
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager = config.authenticationManager

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.invoke {
            csrf { disable() }
            authorizeHttpRequests {
                authorize("/api/**", authenticated)
//                authorize("/auth/**", permitAll)
                authorize(anyRequest, permitAll)
            }
            sessionManagement { SessionCreationPolicy.STATELESS } //Disable HTTP Session
            exceptionHandling { authenticationEntryPoint } //handle JWT exceptions
        }

        //Check for JWT before authentication
        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }

}
package com.sdt.proshop.security.config

import com.sdt.proshop.exceptions.ProShopException
import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.*

@Component
class JwtTokenProvider {

    @Value("\${app.jwt.secret}")
    private lateinit var jwtSecret: String

    @Value("\${app.jwt.expiration-millis}")
    private lateinit var jwtExpiration: String

    @Value("\${app.jwt.refresh-millis}")
    private lateinit var jwtRefresh: String

    /**
     * Creates a JWT
     * @param authentication the currently logged-in user (authenticated)
     * @return a JWT
     */
    fun generateToken(authentication: Authentication): String {
        //Get the user's email
        val username = authentication.name

        //Generate the current date
        val currentDate = Date()
        val expirationDate = Date(currentDate.time + jwtExpiration.toLong())

        return Jwts.builder()
            .claims()
            .subject(username)
            .issuedAt(Date())
            .expiration(expirationDate)
            .and()
            .signWith(secretKey())
            .compact()
    }

    /**
     * Extracts the user's email from the token
     * @param jwt the json web token
     * @return the user's email
     */
    fun extractEmail(jwt: String): String = this.getAllClaims(jwt).subject

    /**
     * Check if the token has expired.
     * @return true if the token has expired
     */
    private fun isExpired(jwt: String): Boolean = this.getAllClaims(jwt)
            .expiration
            .before(Date.from(Instant.now()))

    /**
     * Checks the token for validity
     * @param jwt the json web token
     * @return true, if the token is signed and non-expired.
     */
    fun isValid(jwt: String): Boolean {
        try {
            Jwts.parser()
                .verifyWith(secretKey())
                .build()
                .parse(jwt)

            //If no exception occurs during parsing, then the JWT is valid
            return !isExpired(jwt)
        } catch (ex: Exception) {
            when(ex) {
                is MalformedJwtException -> throw ProShopException(HttpStatus.UNAUTHORIZED, "Invalid token")
                is ExpiredJwtException -> throw ProShopException(HttpStatus.UNAUTHORIZED, "Expired token")
                is UnsupportedJwtException -> throw ProShopException(HttpStatus.UNAUTHORIZED, "Unsupported token")
                is IllegalArgumentException -> throw ProShopException(HttpStatus.BAD_REQUEST, "Token claims string is empty")
                else -> throw ex
            }
        }
    }

    private fun getAllClaims(jwt: String): Claims {
        val parser = Jwts.parser()
            .verifyWith(secretKey())
            .build()

        return parser
            .parseSignedClaims(jwt)
            .payload
    }

    private fun secretKey() = Keys.hmacShaKeyFor(jwtSecret.toByteArray())

}
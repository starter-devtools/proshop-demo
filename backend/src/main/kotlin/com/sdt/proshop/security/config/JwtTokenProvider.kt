package com.sdt.proshop.security.config

import com.sdt.proshop.exceptions.ProShopException
import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtTokenProvider {

    @Value("\${app.jwt.secret}")
    private lateinit var jwtSecret: String

    @Value("\${app.jwt.expiration-millis}")
    private lateinit var jwtExpiration: String

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
            .subject(username)
            .issuedAt(Date())
            .expiration(expirationDate)
            .signWith(this.signingKey())
            .compact()
    }

    private fun signingKey(): SecretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret))

    /**
     * Extracts the user's email from the token
     * @param jwt the json web token
     * @return the user's email
     */
    fun extractUsername(jwt: String): String {
        val parser = Jwts.parser()
            .verifyWith(this.signingKey())
            .build()

         val claims: Claims = parser
            .parseUnsecuredClaims(jwt)
            .payload

        return claims.subject
    }

    /**
     * Checks the token for validity
     * @param jwt the json web token
     * @return true, if the token is signed and non-expired.
     */
    fun validateToken(jwt: String): Boolean {
        try {
            Jwts.parser()
                .verifyWith(this.signingKey())
                .build()
                .parse(jwt)

            //If no exception occurs during parsing, then the JWT is valid
            return true
        } catch (ex: Exception) {
            when(ex) {
                is MalformedJwtException -> throw ProShopException(HttpStatus.BAD_REQUEST, "Invalid token")
                is ExpiredJwtException -> throw ProShopException(HttpStatus.BAD_REQUEST, "Expired token")
                is UnsupportedJwtException -> throw ProShopException(HttpStatus.BAD_REQUEST, "Unsupported token")
                is IllegalArgumentException -> throw ProShopException(HttpStatus.BAD_REQUEST, "Token claims string is empty")
                else -> throw ex
            }
        }
    }

}
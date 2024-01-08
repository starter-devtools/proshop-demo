package com.sdt.proshop.security.models

import jakarta.validation.constraints.Size

class LoginDto(
    @field:Size(min = 6, message = "Email should have at least 6 characters")
    var email: String?,

    @field:Size(min = 8, message = "Password should have at least 8 characters")
    var password: String?
)

data class JwtAuthResponse(
    val accessToken: String,
    val tokenType: String = "Bearer"
)
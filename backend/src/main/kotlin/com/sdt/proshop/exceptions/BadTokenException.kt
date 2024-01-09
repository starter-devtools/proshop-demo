package com.sdt.proshop.exceptions

import org.springframework.http.HttpStatus

data class BadTokenException(
    val status: HttpStatus = HttpStatus.UNAUTHORIZED,
    override val message: String
): RuntimeException(message)
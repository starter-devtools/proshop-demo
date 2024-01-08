package com.sdt.proshop.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
class MissingTokenException(
    val status: HttpStatus? = HttpStatus.UNAUTHORIZED,
    override val message: String
): RuntimeException(message)
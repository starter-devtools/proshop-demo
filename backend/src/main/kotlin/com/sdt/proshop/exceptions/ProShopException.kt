package com.sdt.proshop.exceptions

import org.springframework.http.HttpStatus

data class ProShopException(
    val status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
    override val message: String
): RuntimeException(message)
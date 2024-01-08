package com.sdt.proshop.exceptions.advice

import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.sdt.proshop.extensions.longFormat
import java.time.ZonedDateTime

@JsonPropertyOrder("code", "message", "timestamp")
data class ApiResponse(
    val code: Int,
    val message: String,
    val timestamp: String
) {
    constructor(code: Int, message: String) : this(
        code = code,
        message = message,
        timestamp = ZonedDateTime.now().longFormat()
    )
}

data class ApiError(val fieldName: String? = null, val message: String)

@JsonPropertyOrder("code", "messages", "timestamp")
data class ErrorResponse (
    val code: Int,
    val messages: List<ApiError> = mutableListOf(),
    val timestamp: String,
) {
    constructor(code: Int, messages: List<ApiError>) : this(
        code = code,
        messages = messages,
        timestamp = ZonedDateTime.now().longFormat()
    )
}
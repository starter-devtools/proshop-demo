package com.sdt.proshop.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
class RequestValidationException(
    resourceName: String,
    fields: Map<String, Any?>,
    override val message: String = "'$resourceName' request failed with fields: $fields"
): RuntimeException(message)
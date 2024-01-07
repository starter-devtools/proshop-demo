package com.sdt.proshop.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class ResourceNotFoundException(
    resourceName: String,
    fieldName: String,
    fieldValue: String,
    override val message: String = "$resourceName not found with [$fieldName: $fieldValue]"
): RuntimeException(message)
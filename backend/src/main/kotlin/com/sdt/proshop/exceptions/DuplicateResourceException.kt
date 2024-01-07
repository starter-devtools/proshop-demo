package com.sdt.proshop.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.CONFLICT)
class DuplicateResourceException(
    resourceName: String,
    fieldName: String,
    fieldValue: String,
    override val message: String = "$resourceName already exists with [$fieldName: $fieldValue]"
): RuntimeException(message)
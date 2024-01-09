package com.sdt.proshop.security.models

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Size

class UserDto(
    @field:Size(min = 2, message = "Name should have at least 2 characters")
    var name: String?,

    @field:Size(min = 9, message = "Email should have at least 9 characters")
    var email: String?,

    @JsonProperty(required = false, access = JsonProperty.Access.WRITE_ONLY)
    @field:Size(min = 8, message = "Password should have at least 8 characters")
    var password: String?,

    @JsonProperty(required = false)
    var isAdmin: Boolean = false
) {
    constructor(user: User): this(user.name, user.email, user.password, user.isAdmin)
}

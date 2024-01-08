package com.sdt.proshop.security.models

import jakarta.validation.constraints.Size

class UserDto(
    @field:Size(min = 2, message = "Name should have at least 2 characters")
    var name: String?,

    @field:Size(min = 9, message = "Email should have at least 9 characters")
    var email: String?,

    @field:Size(min = 8, message = "Password should have at least 8 characters")
    var password: String?,
    var isAdmin: Boolean = false
) {
    constructor(user: User): this(user.name, user.email, user.password, user.isAdmin)
}
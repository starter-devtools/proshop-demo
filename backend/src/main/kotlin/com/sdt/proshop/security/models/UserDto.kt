package com.sdt.proshop.security.models

class UserDto(
    var name: String,
    var email: String,
    var password: String,
    var isAdmin: Boolean
) {
    constructor(user: User): this(user.name, user.email, user.password, user.isAdmin)
}
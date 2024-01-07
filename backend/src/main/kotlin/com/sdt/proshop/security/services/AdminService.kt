package com.sdt.proshop.security.services

import com.sdt.proshop.security.models.User
import com.sdt.proshop.security.models.UserDto

interface AdminService {

    fun list(): List<User>
    fun getByEmail(email: String): User?

    fun update(userDto: UserDto)

    fun delete(email: String)

}
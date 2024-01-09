package com.sdt.proshop.security.services

import com.sdt.proshop.security.models.User
import com.sdt.proshop.security.models.UserDto

interface AdminService {

    fun list(): List<User>
    fun getById(id: String): User?
    fun getByEmail(email: String): User?

    fun update(id: String, userDto: UserDto)
    fun updatePassword(id: String, userDto: UserDto)

    fun delete(id: String)

}
package com.sdt.proshop.security.models

import org.springframework.security.core.GrantedAuthority

class Role(var name: String)

class UserRole(
    private var role: Role
): GrantedAuthority {

    override fun getAuthority(): String = this.role.name
}
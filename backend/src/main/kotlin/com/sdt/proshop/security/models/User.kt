package com.sdt.proshop.security.models

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.Instant

@Document("users")
class User(
    @Id val id: String? = null,
    @Indexed(name = "_email_", unique = true) var email: String,
    var name: String,
    var credentials: String,
    var isAdmin: Boolean = false,
    var userRoles: MutableSet<UserRole> = mutableSetOf(),
    val createdAt: Instant = Instant.now(),
    var updatedAt: Instant? = null,
    @Version val version: Int = 0
): UserDetails {

    constructor(userDto: UserDto): this(
        email = userDto.email!!,
        name = userDto.name!!,
        credentials = userDto.password!!,
        isAdmin = userDto.isAdmin
    )

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = this.userRoles

    override fun getPassword() = this.credentials

    override fun getUsername() = this.email

    override fun isAccountNonExpired() = false

    override fun isAccountNonLocked() = false

    override fun isCredentialsNonExpired() = false

    override fun isEnabled() = true

}
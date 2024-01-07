package com.sdt.proshop.security.models

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.Instant

@Document("users")
class User(
    @Id val id: String? = null,
    private var username: String,
    private var password: String,
    private var userRoles: MutableSet<UserRole> = mutableSetOf(),
    val createdAt: Instant = Instant.now(),
    var updatedAt: Instant? = null,
    @Version val version: Int = 0
): UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = this.userRoles

    override fun getPassword() = this.password

    override fun getUsername() = this.username

    override fun isAccountNonExpired() = false

    override fun isAccountNonLocked() = false

    override fun isCredentialsNonExpired() = false

    override fun isEnabled() = true

}
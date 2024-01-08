package com.sdt.proshop.security.models

import com.sdt.proshop.constants.USER_ROLE
import org.springframework.security.core.GrantedAuthority

class Role(
    private var name: String? = USER_ROLE
): GrantedAuthority {

    override fun getAuthority(): String? = this.name
}
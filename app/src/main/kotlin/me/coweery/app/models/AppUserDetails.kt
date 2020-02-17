package me.coweery.app.models

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class AppUserDetails(
    val id: Long,
    email: String,
    password: String,
    authorities: List<GrantedAuthority> = emptyList()
): User(
    email,
    password,
    true,
    true,
    true,
    true,
    authorities
)
package com.beatdjam.book.manager.application.service.security

import com.beatdjam.book.manager.application.service.AuthenticationService
import com.beatdjam.book.manager.domain.enum.RoleType
import com.beatdjam.book.manager.domain.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

class BookManagerUserDetailsService(private val authenticationService: AuthenticationService) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails? {
        return authenticationService.findUser(username)
            ?.let { BookManagerUserDetails(it) }
    }
}

data class BookManagerUserDetails(val id: Long, val email: String, val pass: String, val roleType: RoleType) :
    UserDetails {
    constructor(user: User) : this(user.id, user.email, user.password, user.roleType)

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return AuthorityUtils.createAuthorityList(this.roleType.toString())
    }

    override fun isEnabled(): Boolean = true

    override fun getUsername(): String = this.email

    override fun isCredentialsNonExpired(): Boolean = true

    override fun getPassword(): String = this.pass

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true
}

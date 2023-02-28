package com.beatdjam.book.manager.domain.model

import com.beatdjam.book.manager.domain.enum.RoleType

data class User(
    val id: Long,
    val email: String,
    val password: String,
    val name: String,
    val roleType: RoleType
)

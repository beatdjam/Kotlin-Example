package com.beatdjam.book.manager.domain.repository

import com.beatdjam.book.manager.domain.model.User

interface UserRepository {
    fun find(email: String): User?
}
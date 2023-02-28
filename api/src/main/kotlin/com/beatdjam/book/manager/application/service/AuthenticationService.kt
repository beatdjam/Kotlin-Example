package com.beatdjam.book.manager.application.service

import com.beatdjam.book.manager.domain.model.User
import com.beatdjam.book.manager.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class AuthenticationService(private val userRepository: UserRepository) {
    fun findUser(email: String): User? = userRepository.find(email)
}
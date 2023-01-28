package com.beatdjam.book.manager.application.service

import com.beatdjam.book.manager.domain.model.Book
import com.beatdjam.book.manager.domain.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminBookService(
    private val bookRepository: BookRepository
) {
    @Transactional
    fun register(book: Book) {
        bookRepository.findWithRental(book.id)?.let { throw IllegalArgumentException("Already Exist: ${book.id}") }
        bookRepository.register(book)
    }
}
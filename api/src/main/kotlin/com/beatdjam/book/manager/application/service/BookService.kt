package com.beatdjam.book.manager.application.service

import com.beatdjam.book.manager.domain.model.BookWithRental
import com.beatdjam.book.manager.domain.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(private val bookRepository: BookRepository) {
    fun getList(): List<BookWithRental> {
        return bookRepository.findAllWithRental()
    }

    fun getDetail(bookId: Long): BookWithRental {
        return bookRepository.findWithRental(bookId) ?: throw IllegalArgumentException("Invalid Id: $bookId")
    }
}
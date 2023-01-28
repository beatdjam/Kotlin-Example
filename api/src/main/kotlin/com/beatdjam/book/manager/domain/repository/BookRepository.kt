package com.beatdjam.book.manager.domain.repository

import com.beatdjam.book.manager.domain.model.BookWithRental

interface BookRepository {
    fun findAllWithRental(): List<BookWithRental>
}
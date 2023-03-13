package com.beatdjam.book.manager.domain.repository

import com.beatdjam.book.manager.domain.model.Rental

interface RentalRepository {
    fun startRental(rental: Rental)
    fun endRental(bookId: Long)
}
package com.beatdjam.book.manager.infrastructure.database.repository

import com.beatdjam.book.manager.domain.model.Rental
import com.beatdjam.book.manager.domain.repository.RentalRepository
import com.beatdjam.book.manager.infrastructure.database.mapper.RentalMapper
import com.beatdjam.book.manager.infrastructure.database.mapper.deleteByPrimaryKey
import com.beatdjam.book.manager.infrastructure.database.mapper.insert
import com.beatdjam.book.manager.infrastructure.database.record.RentalRecord
import org.springframework.stereotype.Repository

@Repository
class RentalRepositoryImpl(private val rentalMapper: RentalMapper): RentalRepository {
    override fun startRental(rental: Rental) {
        rentalMapper.insert(toRecord(rental))
    }

    override fun endRental(bookId: Long) {
        rentalMapper.deleteByPrimaryKey(bookId)
    }

    private fun toRecord(model: Rental): RentalRecord {
        return RentalRecord(model.bookId, model.userId,model.rentalDatetime,model.returnDeadline)
    }
}
package com.beatdjam.book.manager.infrastructure.database.repository

import com.beatdjam.book.manager.domain.model.Book
import com.beatdjam.book.manager.domain.model.BookWithRental
import com.beatdjam.book.manager.domain.model.Rental
import com.beatdjam.book.manager.domain.repository.BookRepository
import com.beatdjam.book.manager.infrastructure.database.mapper.BookWithRentalMapper
import com.beatdjam.book.manager.infrastructure.database.mapper.select
import com.beatdjam.book.manager.infrastructure.database.mapper.selectByPrimaryKey
import com.beatdjam.book.manager.infrastructure.database.record.BookWithRentalRecord
import org.springframework.stereotype.Repository

@Repository
class BookRepositoryImpl(private val bookWithRentalMapper: BookWithRentalMapper) : BookRepository {
    override fun findAllWithRental(): List<BookWithRental> {
        return bookWithRentalMapper.select().map { toModel(it) }
    }

    override fun findWithRental(id: Long): BookWithRental? {
        return bookWithRentalMapper.selectByPrimaryKey(id)?.let { toModel(it) }
    }

    private fun toModel(record: BookWithRentalRecord): BookWithRental {
        val book = Book(record.id!!, record.title!!, record.author!!, record.releaseDate!!)
        val rental = record.userId?.let {
            Rental(
                record.id!!,
                record.userId!!,
                record.rentalDatetime!!,
                record.returnDeadLine!!
            )
        }
        return BookWithRental(book, rental)
    }
}
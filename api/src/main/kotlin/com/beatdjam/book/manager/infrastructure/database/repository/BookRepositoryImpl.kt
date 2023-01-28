package com.beatdjam.book.manager.infrastructure.database.repository

import com.beatdjam.book.manager.domain.model.Book
import com.beatdjam.book.manager.domain.model.BookWithRental
import com.beatdjam.book.manager.domain.model.Rental
import com.beatdjam.book.manager.domain.repository.BookRepository
import com.beatdjam.book.manager.infrastructure.database.mapper.*
import com.beatdjam.book.manager.infrastructure.database.record.BookRecord
import com.beatdjam.book.manager.infrastructure.database.record.BookWithRentalRecord
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class BookRepositoryImpl(
    private val bookWithRentalMapper: BookWithRentalMapper,
    private val bookMapper: BookMapper
) : BookRepository {
    override fun findAllWithRental(): List<BookWithRental> {
        return bookWithRentalMapper.select().map { toModel(it) }
    }

    override fun findWithRental(id: Long): BookWithRental? {
        return bookWithRentalMapper.selectByPrimaryKey(id)?.let { toModel(it) }
    }

    override fun register(book: Book) {
        bookMapper.insert(toRecord(book))
    }

    override fun update(id: Long, title: String?, author: String?, releaseDate: LocalDate?) {
        bookMapper.updateByPrimaryKeySelective(BookRecord(id, title, author, releaseDate))
    }

    private fun toRecord(model: Book): BookRecord {
        return BookRecord(model.id, model.title, model.author, model.releaseDate)
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
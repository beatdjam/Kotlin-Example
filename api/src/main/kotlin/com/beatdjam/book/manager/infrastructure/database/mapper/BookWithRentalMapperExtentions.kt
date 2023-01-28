package com.beatdjam.book.manager.infrastructure.database.mapper

import com.beatdjam.book.manager.infrastructure.database.mapper.BookDynamicSqlSupport.Book.author
import com.beatdjam.book.manager.infrastructure.database.mapper.BookDynamicSqlSupport.Book.id
import com.beatdjam.book.manager.infrastructure.database.mapper.BookDynamicSqlSupport.Book.releaseDate
import com.beatdjam.book.manager.infrastructure.database.mapper.BookDynamicSqlSupport.Book.title
import com.beatdjam.book.manager.infrastructure.database.mapper.RentalDynamicSqlSupport.Rental.rentalDatetime
import com.beatdjam.book.manager.infrastructure.database.mapper.RentalDynamicSqlSupport.Rental.returnDeadline
import com.beatdjam.book.manager.infrastructure.database.mapper.RentalDynamicSqlSupport.Rental.userId
import com.beatdjam.book.manager.infrastructure.database.record.BookWithRentalRecord
import org.mybatis.dynamic.sql.SqlBuilder.*
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.from

private val columnList = listOf(
    id,
    title,
    author,
    releaseDate,
    userId,
    rentalDatetime,
    returnDeadline
)

fun BookWithRentalMapper.select(): List<BookWithRentalRecord> {
    val selectStatement = select(columnList).from(BookDynamicSqlSupport.Book, "b") {
        leftJoin(RentalDynamicSqlSupport.Rental, "r") {
            on(id, equalTo(RentalDynamicSqlSupport.Rental.bookId))
        }
    }
    return selectMany(selectStatement)
}

fun BookWithRentalMapper.selectByPrimaryKey(id_: Long): BookWithRentalRecord? {
    val selectStatement = select(columnList).from(BookDynamicSqlSupport.Book, "b") {
        leftJoin(RentalDynamicSqlSupport.Rental, "r") {
            on(id, equalTo(RentalDynamicSqlSupport.Rental.bookId))
        }
        where(id, isEqualTo(id_))
    }
    return selectOne(selectStatement)
}
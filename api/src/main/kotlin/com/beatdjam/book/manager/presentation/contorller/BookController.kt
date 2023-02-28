package com.beatdjam.book.manager.presentation.contorller

import com.beatdjam.book.manager.application.service.BookService
import com.beatdjam.book.manager.presentation.form.BookInfo
import com.beatdjam.book.manager.presentation.form.GetBookDetailResponse
import com.beatdjam.book.manager.presentation.form.GetBookListResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("book")
class BookController(private val service: BookService) {
    @GetMapping("list")
    fun getList(): GetBookListResponse {
        val bookList = service.getList().map { BookInfo(it) }
        return GetBookListResponse(bookList)
    }

    @GetMapping("/detail/{book_id}")
    fun getDetail(@PathVariable("book_id") bookId: Long): GetBookDetailResponse {
        val book = service.getDetail(bookId)
        return GetBookDetailResponse(book)
    }
}
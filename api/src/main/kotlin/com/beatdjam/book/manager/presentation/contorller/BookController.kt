package com.beatdjam.book.manager.presentation.contorller

import com.beatdjam.book.manager.application.service.BookService
import com.beatdjam.book.manager.presentation.form.BookInfo
import com.beatdjam.book.manager.presentation.form.GetBookListResponse
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("book")
@CrossOrigin(origins = ["http://localhost:8081"], allowCredentials = "true")
class BookController(private val service: BookService) {
    @GetMapping("list")
    fun getList(): GetBookListResponse {
        val bookList = service.getList().map { BookInfo(it) }
        return GetBookListResponse(bookList)
    }
}
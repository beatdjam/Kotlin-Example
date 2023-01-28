package com.beatdjam.book.manager.presentation.contorller

import com.beatdjam.book.manager.application.service.AdminBookService
import com.beatdjam.book.manager.domain.model.Book
import com.beatdjam.book.manager.presentation.form.RegisterBookRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("admin/book")
@CrossOrigin(origins = ["http://localhost:8081"], allowCredentials = "true")
class AdminBookController(private val adminBookService: AdminBookService) {
    @PostMapping("/register")
    fun register(@RequestBody request: RegisterBookRequest) {
        adminBookService.register(
            Book(
                request.id,
                request.title,
                request.author,
                request.releaseDate
            )
        )
    }
}
package com.beatdjam.book.manager.presentation.contorller

import com.beatdjam.book.manager.application.service.RentalService
import com.beatdjam.book.manager.application.service.security.BookManagerUserDetails
import com.beatdjam.book.manager.presentation.form.RentalStartRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("rental")
class RentalController(private val rentalService: RentalService) {
    @PostMapping("/start")
    fun startRental(@RequestBody request: RentalStartRequest) {
        val user = SecurityContextHolder.getContext().authentication.principal as BookManagerUserDetails
        rentalService.startRental(request.bookId, user.id)
    }

    @DeleteMapping("/end/{book_id}")
    fun endRental(@PathVariable("book_id") bookId: Long) {
        val user = SecurityContextHolder.getContext().authentication.principal as BookManagerUserDetails
        rentalService.endRental(bookId, user.id)
    }
}
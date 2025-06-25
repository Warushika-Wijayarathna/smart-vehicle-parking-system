package com.zenova.parkingservice.controller;


import com.zenova.parkingservice.dto.BookingDTO;
import com.zenova.parkingservice.dto.PaymentDTO;
import com.zenova.parkingservice.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getBookingById(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        BookingDTO booking = bookingService.getBookingById(id);
        if (booking == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(booking);
    }

    @PostMapping
    public ResponseEntity<BookingDTO> createBooking(@RequestHeader("Authorization") String token, @RequestBody BookingDTO bookingDTO) {

        bookingDTO.setStartTime(LocalDateTime.now());
        bookingDTO.setCreatedAt(LocalDateTime.now());

        return ResponseEntity.ok(bookingService.createBooking(bookingDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingDTO> updateBooking(@RequestHeader("Authorization") String token, @PathVariable Long id, @RequestBody BookingDTO bookingDTO) {
        BookingDTO updatedBooking = bookingService.updateBooking(id, bookingDTO);
        if (updatedBooking == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedBooking);
    }


    @GetMapping("closeBooking/{id}")
    public ResponseEntity<PaymentDTO> closeBooking(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        PaymentDTO paymentDTO = bookingService.closeBooking(id);
        if (paymentDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(paymentDTO);
    }

}

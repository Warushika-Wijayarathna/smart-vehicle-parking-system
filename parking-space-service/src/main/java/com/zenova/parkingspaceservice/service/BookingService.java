package com.zenova.parkingspaceservice.service;


import com.zenova.parkingspaceservice.dto.BookingDTO;

import java.util.List;

public interface BookingService {
    List<BookingDTO> getAllBookings();
    BookingDTO getBookingById(Long id);
    BookingDTO createBooking(BookingDTO bookingDTO);
    void deleteBooking(Long id);

    BookingDTO updateBooking(Long id, BookingDTO bookingDTO);
}

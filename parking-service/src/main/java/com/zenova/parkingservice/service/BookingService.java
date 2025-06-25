package com.zenova.parkingservice.service;


import com.zenova.parkingservice.dto.BookingDTO;
import com.zenova.parkingservice.dto.PaymentDTO;

import java.util.List;

public interface BookingService {
    List<BookingDTO> getAllBookings();
    BookingDTO getBookingById(Long id);
    BookingDTO createBooking(BookingDTO bookingDTO);
    void deleteBooking(Long id);

    BookingDTO updateBooking(Long id, BookingDTO bookingDTO);

    PaymentDTO closeBooking(Long id);
}

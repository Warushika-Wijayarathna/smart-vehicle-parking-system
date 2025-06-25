package com.zenova.parkingspaceservice.service.impl;

import com.zenova.parkingspaceservice.dto.BookingDTO;
import com.zenova.parkingspaceservice.entity.Booking;
import com.zenova.parkingspaceservice.repository.BookingRepository;
import com.zenova.parkingspaceservice.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;

    private static final String USER_SERVICE_URL = "http://user-service/api/user/";

    @Override
    public List<BookingDTO> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(booking -> modelMapper.map(booking, BookingDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public BookingDTO getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        return booking != null ? modelMapper.map(booking, BookingDTO.class) : null;
    }

    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        validateUser(bookingDTO.getUserId());
        Booking booking = modelMapper.map(bookingDTO, Booking.class);
        Booking savedBooking = bookingRepository.save(booking);
        return modelMapper.map(savedBooking, BookingDTO.class);
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public BookingDTO updateBooking(Long id, BookingDTO bookingDTO) {
        Booking existingBooking = bookingRepository.findById(id).orElse(null);
        if (existingBooking == null) {
            return null;
        }
        modelMapper.map(bookingDTO, existingBooking);
        Booking updatedBooking = bookingRepository.save(existingBooking);
        return modelMapper.map(updatedBooking, BookingDTO.class);
    }

    private void validateUser(Long userId) {
        String url = USER_SERVICE_URL + userId;
        ResponseEntity<Void> response = restTemplate.getForEntity(url, Void.class);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new IllegalArgumentException("User with ID " + userId + " does not exist in user-service.");
        }
    }
}

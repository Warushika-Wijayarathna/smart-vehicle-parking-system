package com.zenova.parkingservice.service.impl;


import com.zenova.parkingservice.dto.BookingDTO;
import com.zenova.parkingservice.dto.ParkingSpaceDTO;
import com.zenova.parkingservice.dto.PaymentDTO;
import com.zenova.parkingservice.entity.Booking;
import com.zenova.parkingservice.entity.ParkingSpace;
import com.zenova.parkingservice.repository.BookingRepository;
import com.zenova.parkingservice.service.BookingService;
import com.zenova.parkingservice.service.ParkingSpaceService;
import com.zenova.parkingservice.util.Status;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final ParkingSpaceService parkingSpaceService;
    private final ModelMapper modelMapper;

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
        Booking booking = modelMapper.map(bookingDTO, Booking.class);
        Booking savedBooking = bookingRepository.save(booking);

        if (savedBooking != null) {
            ParkingSpace parkingSpace = booking.getSpace();
            if (parkingSpace != null) {
                parkingSpace.setStatus(Status.RESERVED);
                ParkingSpaceDTO pSpace =parkingSpaceService.updateSpace(parkingSpace.getId(), modelMapper.map(parkingSpace, ParkingSpaceDTO.class));

                if (pSpace != null) {
                    return modelMapper.map(savedBooking, BookingDTO.class);
                }
            }
        }else {
            System.out.println("Error creating booking: Booking could not be saved.");
            // Log the exception if needed
            return null; // Return null or handle the error as appropriate
        }
        return null;
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

    @Override
    public PaymentDTO closeBooking(Long id) {
        final double ratePerMinute = 2.00;
        double payment = 0;

        Booking booking = bookingRepository.findById(id).orElse(null);

        if (booking != null) {
            booking.setEndTime(LocalDateTime.now());

             BookingDTO bookingDTO = updateBooking(id, modelMapper.map(booking, BookingDTO.class) );

             if (bookingDTO != null) {

                 ParkingSpace parkingSpace = booking.getSpace();

                 if (parkingSpace != null) {
                     parkingSpace.setStatus(Status.ACTIVE);
                     ParkingSpaceDTO pSpace =parkingSpaceService.updateSpace(parkingSpace.getId(), modelMapper.map(parkingSpace, ParkingSpaceDTO.class));

                     if (pSpace != null) {

                         // Calculate payment based on the booking duration and parking space rate

                            long durationInMinutes = java.time.Duration.between(booking.getStartTime(), booking.getEndTime()).toMinutes();


                            payment = durationInMinutes * ratePerMinute;

                            PaymentDTO paymentDTO = new PaymentDTO();
                            paymentDTO.setAmount(payment);
                            paymentDTO.setBooking_id(booking.getId());
                            paymentDTO.setUser_id(booking.getUserId());

                         return paymentDTO;
                     }
                 }
             }
        }

        return null;
    }
}

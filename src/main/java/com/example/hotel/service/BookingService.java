package com.example.hotel.service;

import com.example.hotel.model.Booking;
import com.example.hotel.repository.BookingRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> getBookingsForUser(String email) {
        return bookingRepository.findByUserEmail(email);
    }

    public void processNewBooking(Booking booking, String email) {
        // Business Rule: Ensure check-out is after check-in
        if (booking.getCheckInDate() != null && booking.getCheckOutDate() != null) {
            if (booking.getCheckOutDate().isBefore(booking.getCheckInDate())) {
                throw new IllegalArgumentException("Check-out date must be after check-in date!");
            }
        }
        
        booking.setUserEmail(email.toLowerCase());
        bookingRepository.save(booking);
    }
    public void deleteBooking(Long id) {
    bookingRepository.deleteById(id);
}
    public Booking getBookingById(Long id) {
    return bookingRepository.findById(id).orElse(new Booking());
    }
}
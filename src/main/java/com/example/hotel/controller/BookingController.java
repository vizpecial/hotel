package com.example.hotel.controller;

import com.example.hotel.model.Booking;
import com.example.hotel.service.BookingService; // Imported our new layer
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookingController {

    private final BookingService bookingService;

    // The controller now injects the Service, not the Repository!
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
        String defaultUser = "guest@hotel.com";
        
        // Controller calls Service -> Service calls Repository
        model.addAttribute("bookings", bookingService.getBookingsForUser(defaultUser));
        model.addAttribute("newBooking", new Booking());
        return "index"; 
    }

    @PostMapping("/book")
    public String saveBooking(@ModelAttribute("newBooking") Booking booking) {
        String defaultUser = "guest@hotel.com";
        bookingService.processNewBooking(booking, defaultUser);
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
public String deleteBooking(@PathVariable("id") Long id) {
    bookingService.deleteBooking(id);
    return "redirect:/";
}

@GetMapping("/edit/{id}")
public String showEditPage(@PathVariable("id") Long id, Model model) {
    String defaultUser = "guest@hotel.com";
    model.addAttribute("bookings", bookingService.getBookingsForUser(defaultUser));
    
    model.addAttribute("newBooking", bookingService.getBookingById(id)); 
    return "index";
}
}
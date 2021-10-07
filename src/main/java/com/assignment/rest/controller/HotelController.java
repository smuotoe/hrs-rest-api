package com.assignment.rest.controller;

import com.assignment.rest.data.entity.ConfirmationNumber;
import com.assignment.rest.data.entity.Hotel;
import com.assignment.rest.data.entity.Reservation;
import com.assignment.rest.data.repository.HotelRepository;
import com.assignment.rest.data.repository.ReservationRepository;
import com.assignment.rest.exception.HotelNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HotelController {
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    ConfirmationNumber confirmationNumber;

    @GetMapping("/hotels")
    public Iterable<Hotel> getListOfHotels(){
        return this.hotelRepository.findAll();
    }

    @GetMapping("/hotels/{id}")
    Hotel getOneHotel(@PathVariable Long id) {

        return hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException(id));
    }

    @PostMapping("/reservation")
    public ConfirmationNumber reservationConfirmation(@RequestBody Reservation reservation){
        reservationRepository.save(reservation);
        confirmationNumber = new ConfirmationNumber(reservation.getReservationId());
//        confirmationNumber.setConfirmationNumber();
        return confirmationNumber;
    }

}

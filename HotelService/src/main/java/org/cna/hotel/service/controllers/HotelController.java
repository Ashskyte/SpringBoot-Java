package org.cna.hotel.service.controllers;

import org.cna.hotel.service.entities.Hotel;
import org.cna.hotel.service.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    //create
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
    }

    //getAllHotels

    @GetMapping
    public ResponseEntity<List<Hotel>> fetchAllHotels(){
        List<Hotel> hotels = hotelService.getAllHotels();
        return ResponseEntity.ok(hotels);
    }

    //get hotel by id

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> fetchHotelById(@PathVariable String id){
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }
}

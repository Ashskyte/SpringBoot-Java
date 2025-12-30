package org.cna.hotel.service.services.impl;

import org.cna.hotel.service.entities.Hotel;
import org.cna.hotel.service.entities.HotelRepository;
import org.cna.hotel.service.services.HotelService;
import org.cna.hotel.service.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel create(Hotel hotel) {
        String randomId=UUID.randomUUID().toString();
     //   Hotel newHotel=Hotel.builder().about(hotel.getAbout()).id(randomId).name(hotel.getName()).location(hotel.getLocation()).build();
        hotel.setId(randomId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(String id) {
        //todo complete the implementation
        return hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel with given id not found on server!! "+id));
    }
}

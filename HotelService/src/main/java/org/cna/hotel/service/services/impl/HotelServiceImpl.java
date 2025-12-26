package org.cna.hotel.service.services.impl;

import org.cna.hotel.service.entities.Hotel;
import org.cna.hotel.service.entities.HotelRepository;
import org.cna.hotel.service.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;
    @Override
    public Hotel create(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(String id) {
        //todo complete the implementation
        return hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException());
    }
}

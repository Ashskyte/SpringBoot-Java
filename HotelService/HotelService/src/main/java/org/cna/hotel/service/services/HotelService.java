package org.cna.hotel.service.services;

import org.cna.hotel.service.entities.Hotel;

import java.util.List;

public interface HotelService {

    //create

    Hotel create(Hotel hotel);

    //getAll
    List<Hotel> getAllHotels();

    //getHotelById
    Hotel getHotelById(String id);

}

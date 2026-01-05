package org.cna.user.service.userservice.externalservices;

import org.cna.user.service.userservice.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("/hotels/{id}")
    Hotel getHotelById(@PathVariable String id);
}

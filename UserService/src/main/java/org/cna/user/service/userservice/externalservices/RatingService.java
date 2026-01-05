package org.cna.user.service.userservice.externalservices;

import org.cna.user.service.userservice.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @GetMapping("/ratings/users/{userId}")
    List<Rating> getRatingsByUserId(@PathVariable("userId") String userId);
}

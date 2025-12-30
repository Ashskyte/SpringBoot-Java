package org.cna.rating.service.RatingService.controllers;

import org.cna.rating.service.RatingService.entities.Rating;
import org.cna.rating.service.RatingService.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    //create rating
    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating rating) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.createRating(rating));
    }

    //get rating by userid

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingByUserId(userId));
    }

    //get rating by hotelid

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId) {
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingByHotelId(hotelId));
    }

    //get rating by ratingid
    @GetMapping("/{ratingId}")
    public ResponseEntity<Rating> getRatingByRatingId(@PathVariable String ratingId) {
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingByRatingId(ratingId));
    }

    //get all ratings
    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings() {
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getAllRatings());
    }
}

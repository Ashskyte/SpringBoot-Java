package org.cna.rating.service.RatingService.services;

import org.cna.rating.service.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {

    //create
    public Rating createRating(Rating rating);

    //get all ratings
    public List<Rating> getAllRatings();

    //get rating by userid
    public List<Rating> getRatingByUserId(String userId);

    //get rating by hotelid
    public List<Rating> getRatingByHotelId(String hotelId);

    //get rating by ratingID
    public Rating getRatingByRatingId(String ratingId);
}

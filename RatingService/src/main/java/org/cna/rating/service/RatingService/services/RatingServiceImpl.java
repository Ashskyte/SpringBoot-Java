package org.cna.rating.service.RatingService.services;


import org.cna.rating.service.RatingService.entities.Rating;
import org.cna.rating.service.RatingService.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.metrics.DefaultRepositoryTagsProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating createRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }


    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }

    @Override
    public Rating getRatingByRatingId(String ratingId) {
        return ratingRepository.findById(ratingId).get();
    }


}
